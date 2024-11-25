package com.example.iot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DeviceUpdateDTO {

    @NotBlank(message = "Device name cannot be blank")
    @Size(max = 100, message = "Device name cannot exceed 100 characters")
    private String name;

    private String status;

    // Getters and Setters

    public @NotBlank(message = "Device name cannot be blank") @Size(max = 100, message = "Device name cannot exceed 100 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Device name cannot be blank") @Size(max = 100, message = "Device name cannot exceed 100 characters") String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
