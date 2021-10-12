package com.josephrodriguez.learning.springboot.controller;

import com.josephrodriguez.learning.springboot.data.entity.Document;
import com.josephrodriguez.learning.springboot.data.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping("/documents")
    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    @GetMapping("/documents/{code}")
    public ResponseEntity<Document> getDocumentByCode(@PathVariable(value = "code") String code)
        throws ResourceNotFoundException {

        Document document =
                documentRepository
                        .findById(code)
                        .orElseThrow(() -> new ResourceNotFoundException("Document not found :: " + code));

        return ResponseEntity.ok().body(document);
    }

    @PostMapping("/documents")
    public Document createDocument(@RequestBody Document document) {
        return documentRepository.save(document);
    }

    @PutMapping("/documents/{code}")
    public ResponseEntity<Document> updateDocument(
            @PathVariable(value = "code") String code, @RequestBody Document documentDetails)
            throws ResourceNotFoundException {

        Document document =
                documentRepository
                        .findById(code)
                        .orElseThrow(() -> new ResourceNotFoundException("Document not found on :: " + code));

        document.setCode(documentDetails.getCode());
        document.setCodeListCode(documentDetails.getCodeListCode());
        document.setDisplayValue(documentDetails.getDisplayValue());
        document.setLongDescription(documentDetails.getLongDescription());
        document.setFromDate(documentDetails.getFromDate());
        document.setToDate(documentDetails.getToDate());
        document.setSortingPriority(documentDetails.getSortingPriority());

        final Document updatedObservation = documentRepository.save(document);
        return ResponseEntity.ok(updatedObservation);
    }


    @DeleteMapping("/documents/{code}")
    public ResponseEntity<Document> deleteDocument(@PathVariable(value = "code") String code)
        throws ResourceNotFoundException {

        Document observation =
                documentRepository
                        .findById(code)
                        .orElseThrow(() -> new ResourceNotFoundException("Document not found on :: " + code));

        documentRepository.delete(observation);
        return ResponseEntity.ok(observation);
    }

    @DeleteMapping("/documents")
    public ResponseEntity deleteAll() {

        documentRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
