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
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//Lombok annotation
@Slf4j
//Spring annotation
@Service
public class CsvWriterService {

    public ByteArrayInputStream write(Iterable<CsvDocumentDto> documents, String... headers) {

        CSVFormat format = CSVFormat.DEFAULT.withHeader(headers);

        try(ByteArrayOutputStream stream = new ByteArrayOutputStream();
            CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), format)) {

            for (CsvDocumentDto doc : documents) {
                List<String> record = Arrays.asList(
                        doc.getSource(),
                        doc.getCodeListCode(),
                        doc.getCode(),
                        doc.getDisplayValue(),
                        doc.getLongDescription(),
                        String.valueOf(doc.getFromDate()),
                        String.valueOf(doc.getToDate()),
                        String.valueOf(doc.getSortingPriority())
                );
                printer.printRecord(record);
            }
            printer.flush();

            return new ByteArrayInputStream(stream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Csv writing error: " + e.getMessage());
        }
    }

    public <T> ByteArrayInputStream write(Iterable<T> rows, Class<T> clazz) {

        Iterable<ColumnMapping> columns = Arrays.stream(clazz.getDeclaredFields())
                .map(CsvWriterService::getMapping)
                .sorted(Comparator.comparingInt(ColumnMapping::getSort))
                .collect(Collectors.toList());

        for(T row : rows) {
        }
        return null;
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
