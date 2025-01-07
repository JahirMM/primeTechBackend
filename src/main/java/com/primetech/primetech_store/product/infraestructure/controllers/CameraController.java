package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.AddCameraApplication;
import com.primetech.primetech_store.product.application.DTO.camera.*;
import com.primetech.primetech_store.product.application.DeleteCameraApplication;
import com.primetech.primetech_store.product.application.GetCameraApplication;
import com.primetech.primetech_store.product.application.UpdateCameraApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/camera")
@RequiredArgsConstructor
public class CameraController {
    private final AddCameraApplication addCameraApplication;
    private final GetCameraApplication getCameraApplication;
    private final UpdateCameraApplication updateCameraApplication;
    private final DeleteCameraApplication deleteCameraApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddCameraResponseDTO> addMobileDevice(@Valid @RequestBody CameraRequestDTO request, @PathVariable UUID productId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            CameraDTO cameraDTO = addCameraApplication.addMobileDeviceApplication(request, authentication.getName(), productId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new AddCameraResponseDTO("Camera successfully added", cameraDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddCameraResponseDTO("Please log in", null));
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetCameraResponseDTO> getCameras(@PathVariable UUID productId) {
        List<CameraDTO> cameras = getCameraApplication.getCameraApplication(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetCameraResponseDTO(cameras));
    }

    @PutMapping("/{cameraId}")
    public ResponseEntity<UpdateCameraResponseDTO> updateCamera(@PathVariable UUID cameraId, @Valid @RequestBody CameraRequestDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            CameraDTO cameraDTO = updateCameraApplication.updateCameraApplication(authentication.getName(), cameraId, request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UpdateCameraResponseDTO("Camera successfully updated", cameraDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UpdateCameraResponseDTO("Please log in", null));
        }
    }

    @DeleteMapping("/{cameraId}")
    public ResponseEntity<Map<String, String>> deleteCamera(@PathVariable UUID cameraId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> response = new HashMap<>();

        if (authentication != null && authentication.isAuthenticated()) {
            deleteCameraApplication.deleteCameraApplication(cameraId, authentication.getName());
            response.put("message", "Camera deleted correctly");
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(response);
        } else {
            response.put("message", "Please log iny");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }
}
