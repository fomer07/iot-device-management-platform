package com.example.iot.mapper;

import com.example.iot.dto.DeviceDataRequestDTO;
import com.example.iot.dto.DeviceRegistrationDTO;
import com.example.iot.model.DeviceData;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeviceDataMapperTest {

    @Test
    void shouldMapWithProvidedTimestamp() {
        // Given
        LocalDateTime providedTimestamp = LocalDateTime.of(2024, 11, 30, 12, 0);
        DeviceDataMapper deviceDataMapper = new DeviceDataMapper();
        DeviceDataRequestDTO dto = new DeviceDataRequestDTO();

        dto.setData("Temperature : 25.5");
        dto.setTimestamp(providedTimestamp);

        // When
        DeviceData deviceData = deviceDataMapper.toDeviceData(dto);

        // Then
        assertNotNull(deviceData);
        assertEquals("Temperature : 25.5", deviceData.getData());
        assertEquals(providedTimestamp, deviceData.getTimestamp());
    }

    @Test
    void shouldDefaultToCurrentTimestampWhenNotProvided() {
        // Given
        DeviceDataRequestDTO dto = new DeviceDataRequestDTO();
        DeviceDataMapper deviceDataMapper = new DeviceDataMapper();
        dto.setData("Humidity : 55.3");
        dto.setTimestamp(null);

        // When
        DeviceData deviceData = deviceDataMapper.toDeviceData(dto);

        // Then
        assertNotNull(deviceData);
        assertEquals("Humidity : 55.3", deviceData.getData());
        assertNotNull(deviceData.getTimestamp());
        assertTrue(deviceData.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1))); // Timestamp is near "now"
    }
}
