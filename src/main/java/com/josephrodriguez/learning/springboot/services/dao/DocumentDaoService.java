package com.josephrodriguez.learning.springboot.services.dao;

import com.josephrodriguez.learning.springboot.data.entity.Document;
import com.josephrodriguez.learning.springboot.data.repository.DocumentRepository;
import com.josephrodriguez.learning.springboot.dto.http.RestDocumentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentDaoService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<RestDocumentDto> getAll() {
        final List<RestDocumentDto> result = documentRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());

        return result;
    }

    public List<RestDocumentDto> saveAll(List<RestDocumentDto> documents) {

        List<Document> entities = documents
                .stream()
                .map(this::map)
                .collect(Collectors.toList());

        return documentRepository.saveAll(entities)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private RestDocumentDto map(Document doc) {
        return RestDocumentDto.builder()
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

    private Document map(RestDocumentDto doc) {
        return Document.builder()
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
