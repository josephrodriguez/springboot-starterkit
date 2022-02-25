package com.josephrodriguez.springboot.starterkit.controller.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ResponseEntity<String> home() {
        return new ResponseEntity("Home", HttpStatus.OK);
    }
}
