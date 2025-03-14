package com.primetech.primetech_store.product.infrastructure.services;

import com.primetech.primetech_store.common.application.exception.ProductNotFoundException;
import com.primetech.primetech_store.product.domain.interfaces.CameraServiceInterface;
import com.primetech.primetech_store.product.domain.models.Camera;
import com.primetech.primetech_store.product.infrastructure.repositories.CameraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Camera findCameraByCameraId(UUID cameraId) {
        Optional<Camera> cameraOptional = cameraRepository.findByCameraId(cameraId);

        if (cameraOptional.isEmpty()) {
            throw new ProductNotFoundException("Camera not found");
        }

        return cameraOptional.get();
    }

    @Override
    public void deleteCameraByCameraId(UUID cameraId) {
        Optional<Camera> cameraOptional = cameraRepository.findByCameraId(cameraId);

        if (cameraOptional.isEmpty()) {
            throw new ProductNotFoundException("Camera not found");
        }

        cameraRepository.deleteByCameraId(cameraId);
    }
}
