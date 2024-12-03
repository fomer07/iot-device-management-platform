package com.example.iot.controller;

import com.example.iot.dto.DeviceDataRequestDTO;
import com.example.iot.dto.DeviceDataResponse;
import com.example.iot.service.DeviceDataService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices/{deviceId}/data")
public class DeviceDataController {

    private final DeviceDataService deviceDataService;

    public DeviceDataController(DeviceDataService deviceDataService) {
        this.deviceDataService = deviceDataService;
    }

    //TODO List<DeviceDataDTO> ,is a best practice ?
    //TODO test @PathVariable("deviceId") String deviceId is same with @PathVariable String deviceId , parameter
    @GetMapping
    public ResponseEntity<List<DeviceDataResponse>> getDeviceData(@PathVariable("deviceId") String deviceId) {
        List<DeviceDataResponse> data = deviceDataService.getDeviceData(deviceId);
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    //TODO what is use case of @Valid ?
    //TODO UZUN DATA MESAJINDA KAFKA YA GÖNDERİYOR, SQL DE PATLIYOR UZUN DİYOR, AMA 403 ATIYOR ???
    @PostMapping
    public ResponseEntity<String> sendDataToKafka(
            @PathVariable("deviceId") String deviceId,
            @Valid @RequestBody DeviceDataRequestDTO deviceDataRequestDTO) {
        try {
            deviceDataService.sendData(deviceId, deviceDataRequestDTO);
            return ResponseEntity.ok("Data sent to Kafka and stored successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
