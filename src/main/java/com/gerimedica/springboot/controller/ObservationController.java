package com.gerimedica.springboot.controller;

import com.gerimedica.springboot.data.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ObservationController {

    @Autowired
    private ObservationRepository observationRepository;
}
