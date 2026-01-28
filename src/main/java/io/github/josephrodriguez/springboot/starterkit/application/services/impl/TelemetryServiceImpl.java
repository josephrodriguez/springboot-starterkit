package io.github.josephrodriguez.springboot.starterkit.application.services.impl;

import io.github.josephrodriguez.springboot.starterkit.application.ports.TelemetryRepository;
import io.github.josephrodriguez.springboot.starterkit.application.services.TelemetryService;
import io.github.josephrodriguez.springboot.starterkit.domain.IoTDeviceTelemetry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TelemetryServiceImpl implements TelemetryService {

    private final TelemetryRepository repository;

    public TelemetryServiceImpl(TelemetryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<IoTDeviceTelemetry> getByDeviceId(UUID deviceId) {
        return repository.findByDeviceId(deviceId);
    }

    @Override
    public List<IoTDeviceTelemetry> getAll() {
        return repository.findAll();
    }
}
