package com.example.iot.mapper;

import com.example.iot.dto.DeviceRegistrationDTO;
import com.example.iot.dto.DeviceResponse;
import com.example.iot.model.Device;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO public class or class ?
class DeviceMapperTest {

    private final DeviceMapper mapper = new DeviceMapper();

    @Test
    void testToDto() {
        Device device = new Device();
        device.setDeviceId("123");
        device.setName("Device Name");


        DeviceResponse dto = mapper.toResponse(device);

        assertEquals("123", dto.getDeviceId());
        assertEquals("Device Name", dto.getName());


    }

    @Test
    void testToEntity() {
        DeviceRegistrationDTO dto = new DeviceRegistrationDTO();
        dto.setDeviceId("123");
        dto.setName("Device Name");



        Device device = mapper.toEntity(dto);

        assertEquals("123", device.getDeviceId());
        assertEquals("Device Name", device.getName());


    }



}
