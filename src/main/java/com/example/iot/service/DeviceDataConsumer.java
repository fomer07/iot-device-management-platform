package com.example.iot.service;

import com.example.iot.model.DeviceData;
import com.example.iot.repository.DeviceDataRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeviceDataConsumer {

    private final DeviceDataRepository deviceDataRepository;

    public DeviceDataConsumer(DeviceDataRepository deviceDataRepository) {
        this.deviceDataRepository = deviceDataRepository;
    }

    @KafkaListener(topics = "device_data", groupId = "kafka-319df863")
    public void consumeDeviceData(String message) {
        System.out.println("Received data: " + message);

        // Assume the message is JSON formatted: {"deviceId":"12345", "data":"Temperature: 22.5"}
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String deviceId = jsonNode.get("deviceId").asText();
            String data = jsonNode.get("data").asText();

            // Save the data to the database
            DeviceData deviceData = new DeviceData();
            deviceData.setDeviceId(deviceId);
            deviceData.setData(data);
            deviceData.setTimestamp(LocalDateTime.now());

            deviceDataRepository.save(deviceData);

            System.out.println("Data saved for device: " + deviceId);
        }catch (JsonProcessingException e){
            System.err.println("Failed to parse message: " + message);
        }
    }
}
