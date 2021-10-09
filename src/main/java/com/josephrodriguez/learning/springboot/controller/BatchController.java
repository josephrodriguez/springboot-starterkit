package com.josephrodriguez.learning.springboot.controller;

import com.josephrodriguez.learning.springboot.data.repository.ObservationRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/api")
public class BatchController {

    @Autowired
    private ObservationRepository observationRepository;

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        InputStreamReader input = new InputStreamReader(file.getInputStream(), "UTF-8");
        BufferedReader fileReader = new BufferedReader(input);
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
        Iterable<CSVRecord> records = csvParser.getRecords();


        boolean headerReaded = false;
        for (CSVRecord record: records) {
            if(!headerReaded) {
                headerReaded = true;
                continue;
            }

            String a = record.get(0);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/download")
    public ResponseEntity downloadFile(HttpServletRequest request){
        return ResponseEntity.ok().build();
    }
}
