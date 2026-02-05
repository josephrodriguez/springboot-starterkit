package io.github.josephrodriguez.springboot.starterkit.adapters.rest.contracts.dto;

import java.time.OffsetDateTime;

public record SensorDataDto(
        String type,      // e.g. "temperature", "humidity"
        double value,
        String unit,            // e.g. "C", "%", "Pa"
        OffsetDateTime timestamp) {
}
