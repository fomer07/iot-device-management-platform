package com.example.iot.service;

import com.example.iot.dto.DeviceDataRequestDTO;
import com.example.iot.dto.DeviceDataResponse;
import com.example.iot.mapper.DeviceDataMapper;
import com.example.iot.model.DeviceData;
import com.example.iot.repository.DeviceDataRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceDataService {

    private final DeviceDataRepository deviceDataRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final DeviceService deviceService; // To validate device existence
    private final DeviceDataMapper deviceDataMapper;

    public DeviceDataService(DeviceDataRepository deviceDataRepository,
                             KafkaTemplate<String, String> kafkaTemplate,
                             DeviceService deviceService, DeviceDataMapper deviceDataMapper) {
        this.deviceDataRepository = deviceDataRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.deviceService = deviceService;
        this.deviceDataMapper = deviceDataMapper;
    }

    public List<DeviceDataResponse> getDeviceData(String deviceId) {
        List<DeviceData> byDeviceId = deviceDataRepository.findByDeviceId(deviceId);
        List<DeviceDataResponse> collect = byDeviceId.stream()
                .map(deviceDataMapper::toDeviceDataResponse)
                .collect(Collectors.toList());
        return collect;
    }

    public void sendData(String deviceId, DeviceDataRequestDTO deviceDataRequestDTO) {
        if (!deviceService.findDeviceById(deviceId).isPresent()) {
            throw new IllegalArgumentException("Device with this ID does not exist.");
        }

        DeviceData deviceData = deviceDataMapper.toDeviceData(deviceDataRequestDTO);

        //kafkaTemplate.send("device_data", deviceId, deviceData.toString());
        kafkaTemplate.send("device_data",deviceData.getData());

        // Save to database
        deviceDataRepository.save(deviceData);
    }
}
