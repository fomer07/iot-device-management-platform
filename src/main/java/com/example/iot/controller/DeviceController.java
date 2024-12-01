package com.example.iot.controller;

import com.example.iot.dto.DeviceResponse;
import com.example.iot.dto.DeviceRegistrationDTO;
import com.example.iot.dto.DeviceUpdateDTO;
import com.example.iot.mapper.DeviceMapper;
import com.example.iot.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {


    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerDevice(@Valid @RequestBody DeviceRegistrationDTO dto) {
        try {
            String response = deviceService.registerDevice(dto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<DeviceResponse> getDeviceById(@PathVariable String deviceId) {
        return ResponseEntity.ok(deviceService.getDeviceById(deviceId));
    }

    //TODO try withouy RESPONSEENTITY<blablalba>
    @GetMapping
    public ResponseEntity<List<DeviceResponse>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity<String> updateDevice(@PathVariable String deviceId, @Valid @RequestBody DeviceUpdateDTO dto) {
        try {
            String response = deviceService.updateDevice(deviceId, dto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{deviceId}")
    public ResponseEntity<String> deleteDevice(@PathVariable String deviceId) {
        try {
            String response = deviceService.deleteDevice(deviceId);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
