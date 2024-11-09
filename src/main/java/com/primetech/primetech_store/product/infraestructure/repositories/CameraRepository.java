package com.primetech.primetech_store.product.infraestructure.repositories;

import com.primetech.primetech_store.product.domain.models.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CameraRepository extends JpaRepository<Camera, UUID> {
}
