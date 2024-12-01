package com.example.iot.controller;

import com.example.iot.service.DeviceDataService;
import com.example.iot.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeviceDataController.class)
public class DeviceDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceDataService deviceDataService;

    @Test
    public void testPostDeviceData() throws Exception {
        String deviceId = "12345";
        String requestBody = "{\"temperature\": 25, \"humidity\": 50, \"pressure\": 100}";

        mockMvc.perform(post("/api/devices/" + deviceId + "/data")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }
}
