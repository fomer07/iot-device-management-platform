package com.example.iot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class DeviceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;
    private String data;
    private LocalDateTime timestamp;

    public Long getId() {
        return id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getData() {
        return data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
