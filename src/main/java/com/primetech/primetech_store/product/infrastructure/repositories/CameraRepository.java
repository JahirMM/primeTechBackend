package com.primetech.primetech_store.product.infrastructure.repositories;

import com.primetech.primetech_store.product.domain.models.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CameraRepository extends JpaRepository<Camera, UUID> {
    List<Camera> findByDevice_DeviceId(UUID deviceId);
    Optional<Camera> findByCameraId(UUID cameraId);
    void deleteByCameraId(UUID cameraId);
}
