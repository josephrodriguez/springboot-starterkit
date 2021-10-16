package com.josephrodriguez.learning.springboot.controller;

import com.josephrodriguez.learning.springboot.dto.http.DocumentDto;
import com.josephrodriguez.learning.springboot.mapping.DefaultMapper;
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
    private CsvReaderService csvReaderService;

    @Autowired
    private DocumentDaoService documentDaoService;

    @Autowired
    private DefaultMapper mapper;

    @PostMapping("/upload")
    public ResponseEntity<Iterable<DocumentDto>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        InputStreamReader input = new InputStreamReader(file.getInputStream(), "UTF-8");
        List<DocumentDto> documents = csvReaderService.read(input)
                .stream()
                .map(record -> mapper.fromCsv2RestDocument(record))
                .collect(Collectors.toList());

        List<DocumentDto> response = documentDaoService.saveAll(documents);

        return ResponseEntity.ok(response);
    }
}
