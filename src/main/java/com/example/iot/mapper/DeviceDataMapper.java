package com.example.iot.mapper;


import com.example.iot.dto.DeviceDataRequestDTO;
import com.example.iot.dto.DeviceDataResponse;
import com.example.iot.model.DeviceData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DeviceDataMapper {

    public DeviceDataResponse toDeviceDataResponse(DeviceData deviceData) {
        return new DeviceDataResponse(
                deviceData.getDeviceId(),
                deviceData.getData(),
                deviceData.getTimestamp()
        );
    }

    public DeviceData toDeviceData(DeviceDataRequestDTO deviceDataRequestDTO) {
        DeviceData deviceData = new DeviceData();
        deviceData.setDeviceId(deviceDataRequestDTO.getDeviceId());
        deviceData.setData(deviceDataRequestDTO.getData());

        if (deviceDataRequestDTO.getTimestamp() != null) {
            deviceData.setTimestamp(deviceDataRequestDTO.getTimestamp());
        }else {
            deviceData.setTimestamp(LocalDateTime.now());
        }
        return deviceData;
    }
}
