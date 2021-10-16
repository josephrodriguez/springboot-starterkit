package com.josephrodriguez.learning.springboot.services.dao;

import com.josephrodriguez.learning.springboot.data.entity.Document;
import com.josephrodriguez.learning.springboot.data.repository.DocumentRepository;
import com.josephrodriguez.learning.springboot.dto.http.RestDocumentDto;
import com.josephrodriguez.learning.springboot.mapping.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentDaoService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DefaultMapper mapper;

    public List<RestDocumentDto> getAll() {
        final List<RestDocumentDto> result = documentRepository.findAll()
                .stream()
                .map(entity -> mapper.fromDocumentEntity2Dto(entity))
                .collect(Collectors.toList());

        return result;
    }

    public List<RestDocumentDto> saveAll(List<RestDocumentDto> documents) {

        List<Document> entities = documents
                .stream()
                .map(dto -> mapper.fromRestDocument2Entity(dto))
                .collect(Collectors.toList());

        return documentRepository.saveAll(entities)
                .stream()
                .map(entity -> mapper.fromDocumentEntity2Dto(entity))
                .collect(Collectors.toList());
    }
}
