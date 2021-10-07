package com.gerimedica.springboot.controller;

import com.gerimedica.springboot.data.entity.Observation;
import com.gerimedica.springboot.data.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ObservationController {

    @Autowired
    private ObservationRepository observationRepository;

    @GetMapping("/observations")
    public List<Observation> getAll() {
        return observationRepository.findAll();
    }

    @GetMapping("/observations/{code}")
    public ResponseEntity<Observation> getObservationByCode(@PathVariable(value = "code") String code)
        throws ResourceNotFoundException {

        Observation obs =
                observationRepository
                        .findByCode(code)
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new ResourceNotFoundException("Observation not found :: " + code));
        return ResponseEntity.ok().body(obs);
    }
}
