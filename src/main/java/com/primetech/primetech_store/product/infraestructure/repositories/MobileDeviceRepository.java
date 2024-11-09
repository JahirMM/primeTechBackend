package com.primetech.primetech_store.product.infraestructure.repositories;

import com.primetech.primetech_store.product.domain.models.MobileDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MobileDeviceRepository extends JpaRepository<MobileDevice, UUID> {
}
