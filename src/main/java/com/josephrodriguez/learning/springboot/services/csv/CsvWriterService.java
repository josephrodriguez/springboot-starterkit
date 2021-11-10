package com.josephrodriguez.learning.springboot.services.csv;

import com.josephrodriguez.learning.springboot.annotation.CsvColumn;
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
    private Field column;

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

            StreamSupport.stream(rows.spliterator(), false)
                    .map(row -> getRecord(row, columns))
                    .forEach(record -> {
                        try {
                            printer.printRecord(record);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            printer.flush();

            return new ByteArrayInputStream(stream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Csv writing error: " + e.getMessage());
        }
    }

    private static ColumnMapping getMapping(Field field) {
        CsvColumn annotation = field.getAnnotation(CsvColumn.class);
        int sort = annotation.sort();
        String header = StringFuncs.thenIfEmpty(annotation.column(), field.getName());

        return new ColumnMapping(field, sort, header);
    }

    private static <T> List<String> getRecord(T row, Iterable<ColumnMapping> columns) {
        final List<String> record = new ArrayList<>();

        for (final ColumnMapping column : columns) {
            column.field.setAccessible(true);
            Object value = null;

            try {
                value = column.field.get(row);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            record.add(value == null ? "" : String.valueOf(value));
        }

        return record;
    }

    @Getter
    @RequiredArgsConstructor
    static class ColumnMapping {
        private final Field field;
        private final int sort;
        private final String header;
    }
}
