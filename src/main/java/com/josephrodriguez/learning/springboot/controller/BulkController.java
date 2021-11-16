package com.josephrodriguez.learning.springboot.controller;

import com.josephrodriguez.learning.springboot.dto.csv.DocumentCsvDto;
import com.josephrodriguez.learning.springboot.dto.http.DocumentRestDto;
import com.josephrodriguez.learning.springboot.exceptions.CsvException;
import com.josephrodriguez.learning.springboot.services.mapping.DefaultMapper;
import com.josephrodriguez.learning.springboot.services.csv.CsvReaderService;
import com.josephrodriguez.learning.springboot.services.dao.DocumentDaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

//Lombok annotations
@Slf4j
//Spring annotations
@RestController
@RequestMapping("/api")
public class BulkController {

    @Autowired
    private CsvReaderService csvReader;

    @Autowired
    private DocumentDaoService documentDaoService;

    @Autowired
    private DefaultMapper mapper;

    @PostMapping("/upload")
    public ResponseEntity<Iterable<DocumentRestDto>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, CsvException {
        log.info("Logging");

        InputStreamReader input = new InputStreamReader(file.getInputStream(), "UTF-8");
        final List<DocumentRestDto> documents = csvReader.read(input, DocumentCsvDto.class)
                .stream()
                .map(record -> mapper.fromCsv2Rest(record))
                .collect(Collectors.toList());

        List<DocumentRestDto> response = documentDaoService.saveAll(documents);

        return ResponseEntity.ok(response);
    }
}
