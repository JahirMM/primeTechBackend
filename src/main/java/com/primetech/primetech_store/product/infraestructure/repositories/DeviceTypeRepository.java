package com.primetech.primetech_store.product.infraestructure.repositories;

import com.primetech.primetech_store.product.domain.models.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeviceTypeRepository extends JpaRepository<DeviceType, UUID> {
    Optional<DeviceType> findDeviceTypeByTypeName(String typeName);
}
