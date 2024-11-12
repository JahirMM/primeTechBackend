package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.ProductNotFoundException;
import com.primetech.primetech_store.product.application.DTO.screen.ScreenDTO;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ScreenServiceInterface;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.Screen;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GetScreenApplication {
    private final ScreenServiceInterface screenService;
    private final DeviceServiceInterface deviceService;
    private final ProductServiceInterface productService;

    @Transactional
    public List<ScreenDTO> getCameraApplication(UUID productId) {
        if(!productService.existsProductByProductId(productId)) {
            throw new ProductNotFoundException("Product not found");
        }
        Device device = deviceService.findDevicebyProductId(productId);
        List<Screen> screens = screenService.findScreenInformationByDeviceId(device.getDeviceId());
        return screens.stream().map(ScreenDTO::from).collect(Collectors.toList());
    }
}
