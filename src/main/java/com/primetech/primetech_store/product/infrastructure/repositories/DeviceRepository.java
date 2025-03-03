package com.primetech.primetech_store.product.infrastructure.repositories;

import com.primetech.primetech_store.product.domain.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {
    Optional<Device> findByProduct_ProductIdAndDeviceType_DeviceTypeId(UUID productId, UUID deviceTypeId);
    Optional<Device> findByProduct_ProductIdAndDeviceType_DeviceTypeIdIn(UUID productId, List<UUID> deviceTypeIds );
    Optional<Device> findByProduct_ProductId(UUID productId);
    void deleteByDeviceId(UUID deviceId);
}
