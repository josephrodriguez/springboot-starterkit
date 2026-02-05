package io.github.josephrodriguez.springboot.starterkit.application.services;

import io.github.josephrodriguez.springboot.starterkit.domain.IoTDeviceTelemetry;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TelemetryService {

    Optional<IoTDeviceTelemetry> getByDeviceId(UUID deviceId);

    List<IoTDeviceTelemetry> getAll();
}
