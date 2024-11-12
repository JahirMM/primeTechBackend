package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.ProductNotFoundException;
import com.primetech.primetech_store.product.application.DTO.mobileDevice.MobileDeviceDTO;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.MobileDeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.MobileDevice;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GetMobileDeviceApplication {
    private final MobileDeviceServiceInterface mobileDeviceService;
    private final DeviceServiceInterface deviceService;
    private final ProductServiceInterface productService;

    @Transactional
    public List<MobileDeviceDTO> getMobileDeviceApplication(UUID productId) {
        if(!productService.existsProductByProductId(productId)) {
            throw new ProductNotFoundException("Product not found");
        }
        Device device = deviceService.findDevicebyProductId(productId);
        List<MobileDevice> mobileDevices = mobileDeviceService.findMobileDeviceInformationByDeviceId(device.getDeviceId());
        return mobileDevices.stream().map(MobileDeviceDTO::from).collect(Collectors.toList());
    }
}
