package com.example.iot.dto;

import com.example.iot.validation.ValidTimestamp;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class DeviceDataRequestDTO {

    @NotNull(message = "Device ID is required")
    private String deviceId;

    @NotNull(message = "Data is required")
    private String data;

    private @ValidTimestamp LocalDateTime timestamp;

    public @NotNull(message = "Device ID is required") String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(@NotNull(message = "Device ID is required") String deviceId) {
        this.deviceId = deviceId;
    }

    public @NotNull(message = "Data is required") String getData() {
        return data;
    }

    public void setData(@NotNull(message = "Data is required") String data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
