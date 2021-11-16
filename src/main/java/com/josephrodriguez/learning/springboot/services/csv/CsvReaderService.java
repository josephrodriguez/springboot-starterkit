package com.josephrodriguez.learning.springboot.services.csv;

import com.josephrodriguez.learning.springboot.dto.csv.DocumentCsvDto;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CsvReaderService {

    public <T> List<T> read(InputStreamReader input, Class<T> clazz) throws CsvException {
        try(BufferedReader fileReader = new BufferedReader(input);
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT)) {

            Optional<CSVRecord> header = csvParser.stream().limit(1).findFirst();
            List<CSVRecord> records = csvParser.stream().collect(Collectors.toList());

            Integer count = records.size();

//            List<DocumentCsvDto> documents = csvParser.getRecords()
//                    .stream()
//                    .skip(1)
//                    .map(this::map)
//                    .collect(Collectors.toList());

            return Arrays.asList();
        } catch (IOException e) {
            throw new CsvException("Csv reading error: " + e.getMessage());
        }
    }

    /*
    private DocumentCsvDto map(CSVRecord record) {
        return DocumentCsvDto.builder()
                .source(record.get(0))
                .codeListCode(record.get(1))
                .code(record.get(2))
                .displayValue(record.get(3))
                .longDescription(record.get(4))
                .fromDate(DateUtils.parse(record.get(5)))
                .toDate(DateUtils.parse(record.get(6)))
                .sortingPriority(IntegerUtils.parse(record.get(7)))
                .build();
    }
     */
}
