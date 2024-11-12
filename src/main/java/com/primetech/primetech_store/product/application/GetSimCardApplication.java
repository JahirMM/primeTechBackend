package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.ProductNotFoundException;
import com.primetech.primetech_store.product.application.DTO.simCard.SimCardDTO;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.MobileDeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.SimCardServiceInterface;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.MobileDevice;
import com.primetech.primetech_store.product.domain.models.SimCard;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GetSimCardApplication {
    private final SimCardServiceInterface simCardService;
    private final ProductServiceInterface productService;
    private final DeviceServiceInterface deviceService;
    private final MobileDeviceServiceInterface mobileDeviceService;

    @Transactional
    public List<SimCardDTO> getSimCardApplication(UUID productId) {
        if (!productService.existsProductByProductId(productId)) {
            throw new ProductNotFoundException("Product not found");
        }
        Device device = deviceService.findDevicebyProductId(productId);
        List<MobileDevice> mobileDevices = mobileDeviceService.findMobileDeviceInformationByDeviceId(device.getDeviceId());
        if (mobileDevices.isEmpty()) {
            return Collections.emptyList();
        }
        MobileDevice mobileDevice = mobileDevices.get(0);
        List<SimCard> simCards = simCardService.findSimCardInformationByMobileDevice(mobileDevice.getMobileDeviceId());
        return simCards.stream().map(SimCardDTO::from).collect(Collectors.toList());
    }
}
