package com.josephrodriguez.learning.springboot.controller;

import com.josephrodriguez.learning.springboot.dto.http.DocumentDto;
import com.josephrodriguez.learning.springboot.services.dao.DocumentDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DocumentController {

    @Autowired
    private DocumentDaoService daoService;

    @GetMapping("/documents")
    public List<DocumentDto> getAll() {
        return daoService.getAll();
    }

    @GetMapping("/documents/{code}")
    public ResponseEntity<DocumentDto> getByCode(@PathVariable(value = "code") String code)
        throws ResourceNotFoundException {

        DocumentDto document = daoService.findById(code);

        return ResponseEntity.ok().body(document);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/documents")
    public DocumentDto createDocument(@RequestBody DocumentDto document) {
        return daoService.save(document);
    }

    @PutMapping("/documents/{code}")
    public ResponseEntity<DocumentDto> updateDocument(
            @PathVariable(value = "code") String code, @RequestBody DocumentDto document)
            throws ResourceNotFoundException {

        DocumentDto updatedObservation = daoService.save(document);
        return ResponseEntity.ok(updatedObservation);
    }


    @DeleteMapping("/documents/{code}")
    public ResponseEntity<DocumentDto> deleteByCode(@PathVariable(value = "code") String code)
        throws ResourceNotFoundException {

        DocumentDto response = daoService.findById(code);
        daoService.deleteById(code);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/documents")
    public ResponseEntity deleteAll() {

        daoService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
