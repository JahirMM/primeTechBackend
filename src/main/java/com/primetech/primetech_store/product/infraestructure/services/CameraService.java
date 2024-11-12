package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.product.domain.interfaces.CameraServiceInterface;
import com.primetech.primetech_store.product.domain.models.Camera;
import com.primetech.primetech_store.product.infraestructure.repositories.CameraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CameraService implements CameraServiceInterface {
    private final CameraRepository cameraRepository;

    @Override
    public Camera saveCamera(Camera camera) {
        return cameraRepository.save(camera);
    }

    @Override
    public List<Camera> findCameraInformationByDeviceId(UUID deviceId) {
        return cameraRepository.findByDevice_DeviceId(deviceId);
    }
}
