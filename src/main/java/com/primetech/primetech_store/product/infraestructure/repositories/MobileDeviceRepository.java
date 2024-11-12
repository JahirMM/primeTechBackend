package com.primetech.primetech_store.product.infraestructure.repositories;

import com.primetech.primetech_store.product.domain.models.MobileDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MobileDeviceRepository extends JpaRepository<MobileDevice, UUID> {
    Optional<MobileDevice> findByMobileDeviceId(UUID mobileDeviceId);
    List<MobileDevice> findByDevice_DeviceId(UUID deviceId);
}
