package com.primetech.primetech_store.product.domain.interfaces;

import com.primetech.primetech_store.product.domain.models.Camera;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CameraServiceInterface {
    Camera saveCamera(Camera camera);
    List<Camera> findCameraInformationByDeviceId(UUID deviceId);
    Camera findCameraByCameraId(UUID cameraId);
}
