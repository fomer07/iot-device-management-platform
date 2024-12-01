package com.example.iot.dto;


import java.time.LocalDateTime;

public class DeviceResponse {

    private String deviceId;
    private String name;
    private String status;
    private LocalDateTime lastActiveTime;

    public DeviceResponse(String deviceId, String name, String status, LocalDateTime lastActiveTime) {
        this.deviceId = deviceId;
        this.name = name;
        this.status = status;
        this.lastActiveTime = lastActiveTime;
    }

    // Getters and Setters (//TODO use Lombok @Data for brevity)


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(LocalDateTime lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }
}
