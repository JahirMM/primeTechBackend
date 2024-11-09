package com.primetech.primetech_store.product.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCameraResponseDTO {
    private String message;
    private CameraDTO cameraDTO;
}
