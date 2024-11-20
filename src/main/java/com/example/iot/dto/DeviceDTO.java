package com.example.iot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DeviceDTO {

    @NotBlank(message = "Device ID cannot be blank")
    @Size(max = 50, message = "Device ID cannot exceed 50 characters")
    private String deviceId;

    @NotBlank(message = "Device name cannot be blank")
    @Size(max = 100, message = "Device name cannot exceed 100 characters")
    private String name;

    // Add getters and setters
    //TODO is it necessary to make setters for these fields
    public @NotBlank(message = "Device ID cannot be blank") @Size(max = 50, message = "Device ID cannot exceed 50 characters") String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(@NotBlank(message = "Device ID cannot be blank") @Size(max = 50, message = "Device ID cannot exceed 50 characters") String deviceId) {
        this.deviceId = deviceId;
    }

    public @NotBlank(message = "Device name cannot be blank") @Size(max = 100, message = "Device name cannot exceed 100 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Device name cannot be blank") @Size(max = 100, message = "Device name cannot exceed 100 characters") String name) {
        this.name = name;
    }
}
