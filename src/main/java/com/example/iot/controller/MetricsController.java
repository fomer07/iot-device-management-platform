package com.example.iot.controller;

import com.example.iot.repository.DeviceDataRepository;
import com.example.iot.repository.DeviceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("(/api/metrics")
public class MetricsController {

    private final DeviceRepository deviceRepository;
    private final DeviceDataRepository deviceDataRepository;


    public MetricsController(DeviceRepository deviceRepository,
                             DeviceDataRepository deviceDataRepository) {
        this.deviceRepository = deviceRepository;
        this.deviceDataRepository = deviceDataRepository;
    }

    @GetMapping("/device-status")
    public Map<String, Long> getDeviceStatusMetrics() {
        long activeDevices = deviceRepository.countByStatus("active");
        long inactiveDevices = deviceRepository.countByStatus("inactive");

        Map<String, Long> metrics = new HashMap<>();
        metrics.put("activeDevices", activeDevices);
        metrics.put("inactiveDevices", inactiveDevices);
        return metrics;
    }

    @GetMapping("/data-throughput")
    public Map<String, Long> getDataThroughputMetrics() {
        long totalMessages = deviceDataRepository.count();

        Map<String, Long> metrics = new HashMap<>();
        metrics.put("totalMessages", totalMessages);
        return metrics;
    }
}
