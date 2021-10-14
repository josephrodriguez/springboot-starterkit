package com.josephrodriguez.learning.springboot.services.csv;

import com.josephrodriguez.learning.springboot.dto.csv.CsvDocumentDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

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
}
