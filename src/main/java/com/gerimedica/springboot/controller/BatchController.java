package com.gerimedica.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class BatchController {

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/download")
    public ResponseEntity downloadFile(HttpServletRequest request){
        return ResponseEntity.ok().build();
    }
}
