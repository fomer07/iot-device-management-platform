package com.example.iot.repository;

import com.example.iot.model.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceDataRepository extends JpaRepository<DeviceData, Long> {

    List<DeviceData> findByDeviceId(String deviceId);
    long count();

}
