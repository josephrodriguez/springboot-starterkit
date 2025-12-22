package io.github.josephrodriguez.springboot.starterkit.controller;

import io.github.josephrodriguez.springboot.starterkit.responses.StatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@RestController
public class StatusController {

    @GetMapping("/status")
    public ResponseEntity<StatusResponse> getStatus() {

        StatusResponse response = new StatusResponse("running", LocalDateTime.now(ZoneOffset.UTC));
        return ResponseEntity
                .ok()
                .body(response);
    }
}
