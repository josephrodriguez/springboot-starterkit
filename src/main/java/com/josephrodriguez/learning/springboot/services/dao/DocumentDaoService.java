package com.josephrodriguez.learning.springboot.services.dao;

import com.josephrodriguez.learning.springboot.data.entity.DocumentEntity;
import com.josephrodriguez.learning.springboot.data.repository.DocumentRepository;
import com.josephrodriguez.learning.springboot.dto.http.DocumentRestDto;
import com.josephrodriguez.learning.springboot.services.mapping.DefaultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentDaoService {

    @Autowired
    private DocumentRepository repository;

    @Autowired
    private DefaultMapper mapper;

    public List<DocumentRestDto> getAll() {
        final List<DocumentRestDto> result = repository.findAll()
                .stream()
                .map(entity -> mapper.fromEntity2Dto(entity))
                .collect(Collectors.toList());

        return result;
    }

    public List<DocumentRestDto> saveAll(List<DocumentRestDto> documents) {

        List<DocumentEntity> entities = documents
                .stream()
                .map(dto -> mapper.fromRest2Entity(dto))
                .collect(Collectors.toList());

        return repository.saveAll(entities)
                .stream()
                .map(entity -> mapper.fromEntity2Dto(entity))
                .collect(Collectors.toList());
    }

    public DocumentRestDto findById(String code) throws ResourceNotFoundException {

        DocumentEntity entity = repository
                .findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("DocumentEntity not found on :: " + code));

        return mapper.fromEntity2Dto(entity);
    }

    public DocumentRestDto save(DocumentRestDto dto) {

        DocumentEntity mappedEntity = mapper.fromRest2Entity(dto);
        DocumentEntity responseEntity = repository.save(mappedEntity);
        return mapper.fromEntity2Dto(responseEntity);
    }

    public void deleteById(String code) {
        repository.deleteById(code);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
