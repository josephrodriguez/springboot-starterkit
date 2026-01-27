package io.github.josephrodriguez.springboot.starterkit.adapters.rest.controllers.v1;

import io.github.josephrodriguez.springboot.starterkit.adapters.rest.contracts.responses.IoTDeviceTelemetryResponse;
import io.github.josephrodriguez.springboot.starterkit.application.services.TelemetryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/telemetry")
public class TelemetryController {

    private final TelemetryService telemetryService;

    public TelemetryController(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<IoTDeviceTelemetryResponse> getTelemetry(@PathVariable UUID deviceId) {
        return ResponseEntity.ok().body(
                new IoTDeviceTelemetryResponse(
                        UUID.randomUUID(),
                        "", OffsetDateTime.now(), "", 45.65, 67.78, null, null, null
                        ));
    }
}
