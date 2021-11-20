package com.josephrodriguez.learning.springboot.services.csv;

import com.josephrodriguez.learning.springboot.exceptions.CsvException;
import com.josephrodriguez.learning.springboot.utils.DateUtils;
import com.josephrodriguez.learning.springboot.utils.IntegerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CsvReaderService {

    private final CsvColumnMapper csvColumnMapper;
    private final Map<Class<?>, List<CsvColumnMapping>> csvMapping;
    private final Map<Class<?>, Function<String, Object>> functionMap;

    public CsvReaderService(CsvColumnMapper csvColumnMapper) {
        this.csvMapping = new HashMap<>();
        this.csvColumnMapper = csvColumnMapper;
        this.functionMap = loadDefaultFunctions();
    }

    public <T> List<T> read(InputStreamReader input, Class<T> clazz) throws CsvException {
        try(BufferedReader fileReader = new BufferedReader(input)) {

            List<CsvColumnMapping> csvClassMapping = csvMapping.computeIfAbsent(clazz, csvColumnMapper::getClassMapping);
            String[] headers = csvClassMapping.stream()
                    .map(CsvColumnMapping::getHeader)
                    .toArray(String[]::new);

            CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(headers).setSkipHeaderRecord(true).build();
            CSVParser csvParser = new CSVParser(fileReader, csvFormat);

            return csvParser.stream()
                    .map(record -> mapRecord(record, csvClassMapping, clazz))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new CsvException("Csv reading error: " + e.getMessage());
        }
    }

    private <T> Optional<T> mapRecord(CSVRecord record, List<CsvColumnMapping> csvMapping, Class<? extends T> clazz) {

        T instance = null;

        try {
            instance = clazz.newInstance();

            for (CsvColumnMapping mapping: csvMapping) {
                Field field = mapping.getField();
                field.setAccessible(true);

                String value = record.get(mapping.getHeader());
                Function<String, Object> function = functionMap.get(field.getType());

                field.set(instance, function.apply(value));
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(instance);
    }

    private HashMap<Class<?>, Function<String, Object>> loadDefaultFunctions() {

        final HashMap<Class<?>, Function<String, Object>> map = new HashMap<>();
        map.put(String.class, o -> o);
        map.put(Date.class, DateUtils::parse);
        map.put(Integer.class, IntegerUtils::parse);

        return map;
    }
}
