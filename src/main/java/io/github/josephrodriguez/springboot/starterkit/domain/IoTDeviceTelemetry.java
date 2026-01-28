package io.github.josephrodriguez.springboot.starterkit.domain;


import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record IoTDeviceTelemetry(
        UUID deviceId,
        String deviceType,
        OffsetDateTime timestamp,
        String firmwareVersion,
        double batteryLevel,
        double signalStrength,
        String location,
        List<SensorData> sensors,
        List<DeviceEvent> logs
) {}

