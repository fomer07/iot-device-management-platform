package com.example.iot.repository;

import com.example.iot.model.Device;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findByDeviceId(String deviceId);
    long countByStatus(String status);


    @Transactional
    @Modifying
    @Query("DELETE FROM Device d WHERE d.deviceId = :deviceId")
    void deleteByDeviceId(String deviceId);

}
