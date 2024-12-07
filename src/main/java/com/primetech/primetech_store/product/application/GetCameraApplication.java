package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.application.exception.ProductNotFoundException;
import com.primetech.primetech_store.product.application.DTO.camera.CameraDTO;
import com.primetech.primetech_store.product.domain.interfaces.CameraServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Camera;
import com.primetech.primetech_store.product.domain.models.Device;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GetCameraApplication {
    private final CameraServiceInterface cameraService;
    private final DeviceServiceInterface deviceService;
    private final ProductServiceInterface productService;

    @Transactional
    public List<CameraDTO> getCameraApplication(UUID productId) {
        if(!productService.existsProductByProductId(productId)) {
            throw new ProductNotFoundException("Product not found");
        }
        Device device = deviceService.findDevicebyProductId(productId);
        List<Camera> cameras = cameraService.findCameraInformationByDeviceId(device.getDeviceId());
        return cameras.stream().map(CameraDTO::from).collect(Collectors.toList());
    }
}
