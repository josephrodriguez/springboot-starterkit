package io.github.josephrodriguez.springboot.starterkit.domain;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record DeviceEvent(
        UUID eventId,
        OffsetDateTime timestamp,
        String eventType,
        String severity,
        String message
) {}
