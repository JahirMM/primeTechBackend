package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.UserNotSellerException;
import com.primetech.primetech_store.product.application.DTO.camera.CameraRequestDTO;
import com.primetech.primetech_store.product.application.DTO.camera.CameraDTO;
import com.primetech.primetech_store.product.domain.interfaces.CameraServiceInterface;
import com.primetech.primetech_store.product.domain.models.Camera;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class UpdateCameraApplication {
    private final CameraServiceInterface cameraService;
    private final UserServiceInterface userService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;

    @Transactional
    public CameraDTO updateCameraApplication(String email, UUID cameraId, CameraRequestDTO request) {
        if (!userRoleAssignmentService.isSeller(userService.findUserInformationByEmail(email))) {
            throw new UserNotSellerException("The user is not a seller.");
        }

        Camera camera = cameraService.findCameraByCameraId(cameraId);
        camera.setType(request.getType());
        camera.setResolution(request.getResolution());
        camera.setAperture(request.getAperture());
        camera.setOpticalZoom(request.getOpticalZoom());
        camera.setDigitalZoom(request.getDigitalZoom());
        camera.setFeature(request.getFeature());

        Camera saveCamera = cameraService.saveCamera(camera);

        return CameraDTO.from(saveCamera);
    }
}
