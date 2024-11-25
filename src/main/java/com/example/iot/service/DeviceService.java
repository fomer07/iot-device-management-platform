package com.example.iot.service;

import com.example.iot.dto.DeviceRegistrationDTO;
import com.example.iot.dto.DeviceUpdateDTO;
import com.example.iot.model.Device;
import com.example.iot.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Optional<Device> findDeviceById(String deviceId) {
        return deviceRepository.findByDeviceId(deviceId);
    }

    public String registerDevice(DeviceRegistrationDTO dto) {
        if (deviceRepository.findByDeviceId(dto.getDeviceId()).isPresent()) {
            throw new IllegalArgumentException("Device with this ID already exists");
        }

        Device device = new Device();
        device.setDeviceId(dto.getDeviceId());
        device.setName(dto.getName());
        device.setStatus(dto.getStatus());
        device.setLastActive(LocalDateTime.now());
        deviceRepository.save(device);
        return "Device registered successfully";
    }

    public String updateDevice(String deviceId, DeviceUpdateDTO dto) {
        return deviceRepository.findByDeviceId(deviceId)
                .map(device -> {
                    device.setName(dto.getName());
                    device.setStatus(dto.getStatus());
                    device.setLastActive(LocalDateTime.now());
                    deviceRepository.save(device);
                    return "Device updated successfully";
                })
                .orElseThrow(() -> new IllegalArgumentException("Device not found"));
    }

    public String deleteDevice(String deviceId) {
        Optional<Device> device = deviceRepository.findByDeviceId(deviceId);
        if (device.isPresent()) {
            deviceRepository.deleteByDeviceId(deviceId);
            return "Device deleted successfully";
        }
        throw new IllegalArgumentException("Device not found");
    }
}
