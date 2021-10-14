package com.josephrodriguez.learning.springboot.controller;

import com.josephrodriguez.learning.springboot.dto.csv.CsvDocumentDto;
import com.josephrodriguez.learning.springboot.dto.http.RestDocumentDto;
import com.josephrodriguez.learning.springboot.services.csv.CsvWriterService;
import com.josephrodriguez.learning.springboot.services.dao.DocumentDaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
public class DownloadController {

    @Autowired
    private DocumentDaoService documentDaoService;

    @Autowired
    private CsvWriterService csvWriterService;

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(
            @RequestHeader(name = "Content-disposition", defaultValue = "file.csv") final String fileName,
            @RequestHeader(name = "Content-Type", defaultValue = "text/csv") final String mediaType) {

        log.info("Download Csv file.");

        Iterable<CsvDocumentDto> documents = documentDaoService.getAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());

        final String[] headers = { "source", "codeListCode", "code"};
        ByteArrayInputStream inputStream = csvWriterService.write(documents, headers);
        final InputStreamResource resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, fileName)
                .contentType(MediaType.parseMediaType(mediaType))
                .body(resource);
    }

    private CsvDocumentDto map(RestDocumentDto doc) {
        return CsvDocumentDto.builder()
                .source(doc.getSource())
                .codeListCode(doc.getCodeListCode())
                .code(doc.getCode())
                .displayValue(doc.getDisplayValue())
                .longDescription(doc.getLongDescription())
                .fromDate(doc.getFromDate())
                .toDate(doc.getToDate())
                .sortingPriority(doc.getSortingPriority())
                .build();
    }
}
