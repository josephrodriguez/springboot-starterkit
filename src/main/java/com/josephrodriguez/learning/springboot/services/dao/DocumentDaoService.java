package com.josephrodriguez.learning.springboot.services.dao;

import com.josephrodriguez.learning.springboot.data.entity.Document;
import com.josephrodriguez.learning.springboot.data.repository.DocumentRepository;
import com.josephrodriguez.learning.springboot.dto.http.DocumentDto;
import com.josephrodriguez.learning.springboot.mapping.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentDaoService {

    @Autowired
    private DocumentRepository repository;

    @Autowired
    private DefaultMapper mapper;

    public List<DocumentDto> getAll() {
        final List<DocumentDto> result = repository.findAll()
                .stream()
                .map(entity -> mapper.fromDocumentEntity2Dto(entity))
                .collect(Collectors.toList());

        return result;
    }

    public List<DocumentDto> saveAll(List<DocumentDto> documents) {

        List<Document> entities = documents
                .stream()
                .map(dto -> mapper.fromRestDocument2Entity(dto))
                .collect(Collectors.toList());

        return repository.saveAll(entities)
                .stream()
                .map(entity -> mapper.fromDocumentEntity2Dto(entity))
                .collect(Collectors.toList());
    }

    public Optional<DocumentDto> findById(String code) {
        return null;
    }

    public DocumentDto save(DocumentDto document) {
        return null;
    }

    public void deleteById(String code) {
        repository.deleteById(code);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
