package com.josephrodriguez.springboot.starterkit.controller.api;

import com.josephrodriguez.springboot.starterkit.responses.StatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@RestController
public class StatusController {

    @RequestMapping("/status")
    public ResponseEntity<StatusResponse> getStatus() {

        StatusResponse response = new StatusResponse("running", LocalDateTime.now(ZoneOffset.UTC));
        return ResponseEntity
                .ok()
                .body(response);
    }
}
