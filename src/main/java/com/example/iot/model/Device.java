package com.example.iot.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Device {

    //TODO hangilerine getter ve setter set edilmeli ? best practice

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // To prevent potential problems with duplicate deviceId values,
    // we must ensure that the deviceId is unique at the database level.
    // this approach guarantees proper identification for operations like deletion.
    @Column(unique = true, nullable = false)
    private String deviceId;


    private String name;
    private String status;
    private LocalDateTime lastActive;

    public Long getId() {
        return id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getLastActive() {
        return lastActive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLastActive(LocalDateTime lastActive) {
        this.lastActive = lastActive;
    }
}
