package com.josephrodriguez.learning.springboot.controller;

import com.josephrodriguez.learning.springboot.dto.http.DocumentRestDto;
import com.josephrodriguez.learning.springboot.services.dao.DocumentDaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class DocumentController {

    private final DocumentDaoService daoService;

    @GetMapping("/documents")
    public List<DocumentRestDto> getAll() {
        return daoService.getAll();
    }

    @GetMapping("/documents/{code}")
    public ResponseEntity<DocumentRestDto> getByCode(@PathVariable(value = "code") String code)
        throws ResourceNotFoundException {

        DocumentRestDto document = daoService.findById(code);

        return ResponseEntity.ok().body(document);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/documents")
    public DocumentRestDto createDocument(@RequestBody DocumentRestDto document) {
        return daoService.save(document);
    }

    @PutMapping("/documents/{code}")
    public ResponseEntity<DocumentRestDto> updateDocument(
            @PathVariable(value = "code") String code, @RequestBody DocumentRestDto document)
            throws ResourceNotFoundException {

        DocumentRestDto updatedObservation = daoService.save(document);
        return ResponseEntity.ok(updatedObservation);
    }


    @DeleteMapping("/documents/{code}")
    public ResponseEntity<DocumentRestDto> deleteByCode(@PathVariable(value = "code") String code)
        throws ResourceNotFoundException {

        DocumentRestDto response = daoService.findById(code);
        daoService.deleteById(code);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/documents")
    public ResponseEntity deleteAll() {

        daoService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
