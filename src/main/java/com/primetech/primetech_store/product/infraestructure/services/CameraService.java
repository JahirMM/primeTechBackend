package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.product.domain.interfaces.CameraServiceInterface;
import com.primetech.primetech_store.product.domain.models.Camera;
import com.primetech.primetech_store.product.infraestructure.repositories.CameraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CameraService implements CameraServiceInterface {
    private final CameraRepository cameraRepository;

    @Override
    public Camera saveCamera(Camera camera) {
        return cameraRepository.save(camera);
    }
}
