package io.github.josephrodriguez.springboot.starterkit.domain;

import java.time.OffsetDateTime;

public record SensorData(
        String sensorType,
        double value,
        String unit,
        OffsetDateTime timestamp
) {}
