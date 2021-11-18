package com.josephrodriguez.learning.springboot.services.csv;

import com.josephrodriguez.learning.springboot.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class CsvWriterService {

    private final CsvColumnMapper csvMapper;

    private final HashMap<Class<?>, List<CsvColumnMapping>> columnMap;

    public CsvWriterService(CsvColumnMapper csvMapper) {
        columnMap = new HashMap<>();
        this.csvMapper = csvMapper;
    }

    public <T> ByteArrayInputStream write(List<T> rows, Class<T> clazz) throws CsvException {

        List<CsvColumnMapping> csvClassMapping = columnMap.computeIfAbsent(clazz, csvMapper::getClassMapping);

        String[] headers = csvClassMapping.stream()
                .map(CsvColumnMapping::getHeader)
                .toArray(String[]::new);

        CSVFormat format = CSVFormat.DEFAULT. builder().setHeader(headers).build();

        try(ByteArrayOutputStream stream = new ByteArrayOutputStream();
            CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), format)) {

            StreamSupport.stream(rows.spliterator(), false)
                    .map(row -> getRecord(row, csvClassMapping))
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
            throw new CsvException("Csv writing error: " + e.getMessage());
        }
    }

    private <T> List<String> getRecord(T row, List<CsvColumnMapping> csvColumnMappings) {
        final List<String> record = new ArrayList<>();

        for (CsvColumnMapping csvColumnMapping : csvColumnMappings) {
            csvColumnMapping.getField().setAccessible(true);
            Object value = null;

            try {
                value = csvColumnMapping.getField().get(row);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            record.add(value == null ? "" : String.valueOf(value));
        }

        return record;
    }
}
