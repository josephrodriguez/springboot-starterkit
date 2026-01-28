package io.github.josephrodriguez.springboot.starterkit.adapters.rest.contracts.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record DeviceEventDto(
        UUID eventId,
        OffsetDateTime timestamp,
        String eventType,              // e.g. "BatteryLow"
        String severity,               // Info, Warning, Error
        String message
) {}