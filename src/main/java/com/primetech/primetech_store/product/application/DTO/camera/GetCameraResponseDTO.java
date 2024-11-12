package com.primetech.primetech_store.product.application.DTO.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCameraResponseDTO {
    private List<CameraDTO> camera;
}
