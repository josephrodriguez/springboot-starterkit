package io.github.josephrodriguez.springboot.starterkit.application.ports;

import io.github.josephrodriguez.springboot.starterkit.domain.IoTDeviceTelemetry;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TelemetryRepository {

    Optional<IoTDeviceTelemetry> findByDeviceId(UUID deviceId);

    List<IoTDeviceTelemetry> findAll();
}
