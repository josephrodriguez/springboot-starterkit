package io.github.josephrodriguez.springboot.starterkit.adapters.rest.controllers.v1;

import io.github.josephrodriguez.springboot.starterkit.application.services.TelemetryService;
import io.github.josephrodriguez.springboot.starterkit.domain.IoTDeviceTelemetry;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static io.github.josephrodriguez.springboot.starterkit.adapters.rest.constants.MediaTypes.TELEMETRY_V1_JSON;

@RestController
@RequestMapping("/api/telemetry")
@Tag(name = "Telemetry", description = "IoT device telemetry")
public class TelemetryController {

    private final TelemetryService telemetryService;

    public TelemetryController(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    @GetMapping(value = "/{deviceId}", produces = TELEMETRY_V1_JSON)
    public ResponseEntity<IoTDeviceTelemetry> getDeviceById(@PathVariable UUID deviceId) {
        return telemetryService.getByDeviceId(deviceId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(produces = TELEMETRY_V1_JSON)
    public ResponseEntity<List<IoTDeviceTelemetry>> getAllDevices() {
        var devices = telemetryService.getAll();

        return devices.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(devices);
    }
}
