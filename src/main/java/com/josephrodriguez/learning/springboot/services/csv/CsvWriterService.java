package com.josephrodriguez.learning.springboot.services.csv;

import com.josephrodriguez.learning.springboot.annotation.CsvColumn;
import com.josephrodriguez.learning.springboot.dto.csv.CsvDocumentDto;
import com.josephrodriguez.learning.springboot.utils.StringFuncs;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class CsvWriterService {

    private HashMap<Class<?>, Iterable<ColumnMapping>> columnMap;

    public CsvWriterService() {
        columnMap = new HashMap<>();
    }

    public <T> ByteArrayInputStream write(Iterable<T> rows, Class<T> clazz) {

        Iterable<ColumnMapping> columns = columnMap.computeIfAbsent(clazz,
                cl -> Arrays.stream(clazz.getDeclaredFields())
                        .map(CsvWriterService::getMapping)
                        .sorted(Comparator.comparingInt(ColumnMapping::getSort))
                        .collect(Collectors.toList()));

        String[] headers = StreamSupport.stream(columns.spliterator(), false)
                .map(cl -> cl.header)
                .toArray(String[]::new);

        CSVFormat format = CSVFormat.DEFAULT.withHeader(headers);

        try(ByteArrayOutputStream stream = new ByteArrayOutputStream();
            CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), format)) {

            for(T row : rows) {
                List<String> record = new ArrayList<>();

                for (final ColumnMapping column : columns) {
                    column.field.setAccessible(true);

                    try {
                        Object value = column.field.get(row);
                        record.add(String.valueOf(value));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                printer.printRecord(record);
            }
            printer.flush();
            return new ByteArrayInputStream(stream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Csv writing error: " + e.getMessage());
        }
    }

    static ColumnMapping getMapping(Field field) {
        CsvColumn annotation = field.getAnnotation(CsvColumn.class);
        int sort = annotation.sort();
        String header = StringFuncs.thenIfEmpty(annotation.column(), field.getName());

        return new ColumnMapping(field, sort, header);
    }

    @Getter
    @RequiredArgsConstructor
    static class ColumnMapping {
        private final Field field;
        private final int sort;
        private final String header;
    }
}
