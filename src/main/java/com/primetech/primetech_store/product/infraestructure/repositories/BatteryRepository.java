package com.primetech.primetech_store.product.infraestructure.repositories;

import com.primetech.primetech_store.product.domain.models.Battery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BatteryRepository extends JpaRepository<Battery, UUID> {
    boolean existsByDevice_DeviceId(UUID deviceId);
    List<Battery> findByDevice_DeviceId(UUID deviceId);
    Optional<Battery> findByBatteryId(UUID batteryId);
}
