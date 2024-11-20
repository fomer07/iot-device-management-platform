package com.example.iot.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeviceDataProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public DeviceDataProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendDeviceData(String deviceId, String data) {
        kafkaTemplate.send("device_data", deviceId, data);
    }
}
