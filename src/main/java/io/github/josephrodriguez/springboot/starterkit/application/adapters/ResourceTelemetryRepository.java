package io.github.josephrodriguez.springboot.starterkit.application.adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.josephrodriguez.springboot.starterkit.application.ports.TelemetryRepository;
import io.github.josephrodriguez.springboot.starterkit.domain.IoTDeviceTelemetry;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ResourceTelemetryRepository implements TelemetryRepository {

    private final ObjectMapper objectMapper;
    private final Map<UUID, IoTDeviceTelemetry> cache = new ConcurrentHashMap<>();

    public ResourceTelemetryRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void loadData() {
        try {
            InputStream is = new ClassPathResource("data/telemetry-devices.json").getInputStream();

            List<IoTDeviceTelemetry> devices = objectMapper.readValue(is, new TypeReference<>() {});

            devices.forEach(d ->
                    cache.put(d.deviceId(), d)
            );

        } catch (Exception ex) {
            throw new IllegalStateException("Failed to load telemetry data", ex);
        }
    }

    @Override
    public Optional<IoTDeviceTelemetry> findByDeviceId(UUID deviceId) {
        return Optional.ofNullable(cache.get(deviceId));
    }

    @Override
    public List<IoTDeviceTelemetry> findAll() {
        return List.copyOf(cache.values());
    }
}
