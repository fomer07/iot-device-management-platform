package com.example.iot.controller;

import com.example.iot.dto.DeviceRegistrationDTO;
import com.example.iot.dto.DeviceUpdateDTO;
import com.example.iot.model.Device;
import com.example.iot.model.DeviceData;
import com.example.iot.repository.DeviceDataRepository;
import com.example.iot.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;
    private final DeviceDataRepository deviceDataRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public DeviceController(DeviceService deviceService, DeviceDataRepository deviceDataRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.deviceService = deviceService;
        this.deviceDataRepository = deviceDataRepository;
        this.kafkaTemplate = kafkaTemplate;
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

    //TODO try withouy RESPONSEENTITY<blablalba>
    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @GetMapping("/{deviceId}/data")
    public ResponseEntity<List<DeviceData>> getDeviceData(@PathVariable String deviceId) {
        List<DeviceData> data = deviceDataRepository.findByDeviceId(deviceId);
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @PostMapping("/{deviceId}/data")
    public ResponseEntity<String> sendDataToKafka(
            @PathVariable String deviceId,
            @RequestBody DeviceData deviceData) {
        // Check if the device exists
        if (!deviceService.findDeviceById(deviceId).isPresent()) {
            return ResponseEntity.badRequest().body("Device with this ID does not exist.");
        }

        // Set the device ID in the data object (optional if not part of the payload)
        deviceData.setDeviceId(deviceId);
        deviceData.setTimestamp(LocalDateTime.now());

        // Send data to Kafka
        kafkaTemplate.send("device_data", deviceId, deviceData.toString());

        // Optionally save the data in your database for auditing //TODO auditing?
        deviceDataRepository.save(deviceData);

        return ResponseEntity.ok("Data sent to Kafka and stored successfully.");
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
