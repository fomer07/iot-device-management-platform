package com.example.iot.mapper;


import com.example.iot.dto.DeviceRegistrationDTO;
import com.example.iot.dto.DeviceResponse;
import com.example.iot.model.Device;
import org.springframework.stereotype.Component;


@Component
public class DeviceMapper {

    public DeviceResponse toResponse(Device device) {
        return new DeviceResponse(
                device.getDeviceId(),
                device.getName(),
                device.getStatus(),
                device.getLastActive()
        );
    }

    public Device toEntity(DeviceRegistrationDTO deviceRegistrationDTO) {
       Device device = new Device();
       device.setDeviceId(deviceRegistrationDTO.getDeviceId());
       device.setName(deviceRegistrationDTO.getName());
       device.setStatus(deviceRegistrationDTO.getStatus());
       return device;
    }


}
