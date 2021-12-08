package com.josephrodriguez.learning.springboot.controller;

import com.josephrodriguez.learning.springboot.dto.csv.DocumentCsvDto;
import com.josephrodriguez.learning.springboot.exceptions.CsvException;
import com.josephrodriguez.learning.springboot.services.mapping.DefaultMapper;
import com.josephrodriguez.learning.springboot.services.csv.CsvWriterService;
import com.josephrodriguez.learning.springboot.services.dao.DocumentDaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class DownloadController {

    private final DocumentDaoService documentDaoService;
    private final CsvWriterService csvWriter;
    private final DefaultMapper mapper;

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(
            @RequestHeader(name = "Content-disposition", defaultValue = "file.csv") final String fileName,
            @RequestHeader(name = "Content-Type", defaultValue = "text/csv") final String mediaType) throws CsvException {

        List<DocumentCsvDto> documents = documentDaoService.getAll()
                .stream()
                .map(rest -> mapper.fromRest2Csv(rest))
                .collect(Collectors.toList());

        ByteArrayInputStream inputStream = csvWriter.write(documents, DocumentCsvDto.class);

        final InputStreamResource resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, fileName)
                .contentType(MediaType.parseMediaType(mediaType))
                .body(resource);
    }
}
