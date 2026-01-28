package io.github.josephrodriguez.springboot.starterkit.adapters.rest.contracts.responses;

import io.github.josephrodriguez.springboot.starterkit.adapters.rest.contracts.dto.DeviceEventDto;
import io.github.josephrodriguez.springboot.starterkit.adapters.rest.contracts.dto.SensorDataDto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record IoTDeviceTelemetryResponse(
        UUID deviceId,
        String deviceType,
        OffsetDateTime timestamp,
        String firmwareVersion,
        double batteryLevel,
        double signalStrength,
        String location,
        SensorDataDto[] sensors,
        DeviceEventDto[] logs
) {
}
