package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.AddCameraApplication;
import com.primetech.primetech_store.product.application.DTO.camera.AddCameraRequestDTO;
import com.primetech.primetech_store.product.application.DTO.camera.AddCameraResponseDTO;
import com.primetech.primetech_store.product.application.DTO.camera.CameraDTO;
import com.primetech.primetech_store.product.application.DTO.camera.GetCameraResponseDTO;
import com.primetech.primetech_store.product.application.GetCameraApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/camera")
@RequiredArgsConstructor
public class CameraController {
    private final AddCameraApplication addCameraApplication;
    private final GetCameraApplication getCameraApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddCameraResponseDTO> addMobileDevice(@Valid @RequestBody AddCameraRequestDTO request, @PathVariable UUID productId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            CameraDTO cameraDTO = addCameraApplication.addMobileDeviceApplication(request, authentication.getName(), productId);
            return ResponseEntity.status(HttpStatus.OK)
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
}
