package com.example.iot.controller;

import com.example.iot.model.Device;
import com.example.iot.model.DeviceData;
import com.example.iot.repository.DeviceDataRepository;
import com.example.iot.repository.DeviceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceDataRepository deviceDataRepository;
    private DeviceRepository deviceRepository;

    public DeviceController(DeviceRepository deviceRepository, DeviceDataRepository deviceDataRepository) {
        this.deviceRepository = deviceRepository;
        this.deviceDataRepository = deviceDataRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerDevice(@RequestBody Device device) {
        if (deviceRepository.findByDeviceId(device.getDeviceId()).isPresent()) {
            return ResponseEntity.badRequest().body("Device with this ID already exists");
        }

        device.setLastActive(LocalDateTime.now());
        deviceRepository.save(device);
        return ResponseEntity.ok("Device registered successfully");
    }

    //TODO try withouy RESPONSEENTITY<blablalba>
    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.ok(deviceRepository.findAll());
    }

    @GetMapping("/{deviceId}/data")
    public ResponseEntity<List<DeviceData>> getDeviceData(@PathVariable String deviceId) {
        List<DeviceData> data = deviceDataRepository.findByDeviceId(deviceId);
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }


    @PutMapping("/{deviceId}")
    public ResponseEntity<String> updateDevice(@PathVariable String deviceId, @RequestBody Device updatedDevice) {
        return deviceRepository.findByDeviceId(deviceId)
                .map(device -> {
                    device.setName(updatedDevice.getName());
                    device.setStatus(updatedDevice.getStatus());
                    device.setLastActive(LocalDateTime.now());
                    deviceRepository.save(device);
                    return ResponseEntity.ok("Device updated successfully");
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{deviceId}")
    public ResponseEntity<String> deleteDevice(@PathVariable String deviceId) {
        if (deviceRepository.findByDeviceId(deviceId).isPresent()) {
            deviceRepository.deleteByDeviceId(deviceId);
            return ResponseEntity.ok("Device deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

}
