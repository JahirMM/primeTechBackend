package com.primetech.primetech_store.product.infraestructure.repositories;

import com.primetech.primetech_store.product.domain.models.Battery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BatteryRepository extends JpaRepository<Battery, UUID> {
    boolean existsByDevice_DeviceId(UUID deviceId);
}
