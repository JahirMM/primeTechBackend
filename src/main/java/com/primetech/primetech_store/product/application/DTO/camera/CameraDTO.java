package com.primetech.primetech_store.product.application.DTO.camera;

import com.primetech.primetech_store.product.domain.models.Camera;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CameraDTO {
    private String type;
    private String resolution;
    private String aperture;
    private String opticalZoom;
    private String digitalZoom;
    private String feature;

    public static CameraDTO from(Camera camera) {
        return new CameraDTO(
                camera.getType(),
                camera.getResolution(),
                camera.getAperture(),
                camera.getOpticalZoom(),
                camera.getDigitalZoom(),
                camera.getFeature()
        );
    }
}
