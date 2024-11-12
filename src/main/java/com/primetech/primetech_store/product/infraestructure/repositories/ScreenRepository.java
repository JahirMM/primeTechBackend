package com.primetech.primetech_store.product.infraestructure.repositories;

import com.primetech.primetech_store.product.domain.models.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScreenRepository extends JpaRepository<Screen, UUID> {
    boolean existsByDevice_DeviceId(UUID deviceId);
    List<Screen> findByDevice_DeviceId(UUID deviceId);
}
