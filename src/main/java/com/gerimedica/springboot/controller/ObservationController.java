package com.gerimedica.springboot.controller;

import com.gerimedica.springboot.data.entity.Observation;
import com.gerimedica.springboot.data.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/observations")
    public Observation createObservation(@RequestBody Observation observation) {
        return observationRepository.save(observation);
    }

    @PutMapping("/observations/{id}")
    public ResponseEntity<Observation> updateObservation(
            @PathVariable(value = "id") Long observationId, @RequestBody Observation observationDetails)
            throws ResourceNotFoundException {

        Observation observation =
                observationRepository
                        .findById(observationId)
                        .orElseThrow(() -> new ResourceNotFoundException("Observation not found on :: " + observationId));

        observation.setCode(observationDetails.getCode());
        observation.setCodeListCode(observationDetails.getCodeListCode());
        observation.setDisplayValue(observationDetails.getDisplayValue());
        observation.setLongDescription(observationDetails.getLongDescription());
        observation.setFromDate(observationDetails.getFromDate());
        observation.setToDate(observationDetails.getToDate());
        observation.setSortingPriority(observationDetails.getSortingPriority());

        final Observation updatedObservation = observationRepository.save(observation);
        return ResponseEntity.ok(updatedObservation);
    }


    @DeleteMapping("/observations/{id}")
    public ResponseEntity<Observation> deleteObservation(@PathVariable(value = "id") Long observationId)
        throws ResourceNotFoundException {

        Observation observation =
                observationRepository
                        .findById(observationId)
                        .orElseThrow(() -> new ResourceNotFoundException("Observation not found on :: " + observationId));

        observationRepository.delete(observation);
        return ResponseEntity.ok(observation);
    }

    @DeleteMapping("/observations")
    public ResponseEntity deleteAll() {

        observationRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
