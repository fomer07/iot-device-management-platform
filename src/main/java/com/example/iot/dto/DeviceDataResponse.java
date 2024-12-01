package com.example.iot.dto;

import java.time.LocalDateTime;

public class DeviceDataResponse {

    private String deviceId;
    private String data;
    private LocalDateTime timestamp;

    public DeviceDataResponse(String deviceId, String data, LocalDateTime timestamp) {
        this.deviceId = deviceId;
        this.data = data;
        this.timestamp = timestamp;
    }

    // Getters and Setters

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
