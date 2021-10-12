package com.josephrodriguez.learning.springboot.controller;

import com.josephrodriguez.learning.springboot.data.entity.Document;
import com.josephrodriguez.learning.springboot.data.repository.DocumentRepository;
import com.josephrodriguez.learning.springboot.dto.DocumentDto;
import com.josephrodriguez.learning.springboot.utils.mapping.CsvRecordMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BulkController {

    @Autowired
    private DocumentRepository documentRepository;

    @PostMapping("/upload")
    public ResponseEntity<Iterable<DocumentDto>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        InputStreamReader input = new InputStreamReader(file.getInputStream(), "UTF-8");
        BufferedReader fileReader = new BufferedReader(input);
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

        Iterable<Document> documents = csvParser.getRecords()
                .stream()
                .skip(1)
                .map(CsvRecordMapper::toDocument)
                ::iterator;

        Iterable<DocumentDto> response = documentRepository.saveAll(documents)
                .stream()
                .map(doc -> new DocumentDto() {{
                    setCode(doc.getCode());
                    setCodeListCode(doc.getCodeListCode());
                    setSource(doc.getSource());
                    setDisplayValue(doc.getDisplayValue());
                    setLongDescription(doc.getLongDescription());
                    setFrom(doc.getFromDate());
                    setTo(doc.getToDate());
                    setSortingPriority(doc.getSortingPriority());
                }})
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/download")
    public ResponseEntity downloadFile(HttpServletRequest request){
        return ResponseEntity.ok().build();
    }
}
