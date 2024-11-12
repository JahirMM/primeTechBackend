package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.ProductNotFoundException;
import com.primetech.primetech_store.product.application.DTO.battery.BatteryDTO;
import com.primetech.primetech_store.product.domain.interfaces.BatteryServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Battery;
import com.primetech.primetech_store.product.domain.models.Device;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GetBatteryApplication {
    private  final BatteryServiceInterface batteryService;
    private final DeviceServiceInterface deviceService;
    private final ProductServiceInterface productService;

    @Transactional
    public List<BatteryDTO> getBatteryApplication(UUID productId) {
        if(!productService.existsProductByProductId(productId)) {
            throw new ProductNotFoundException("Product not found");
        }
        Device device = deviceService.findDevicebyProductId(productId);
        List<Battery> batteries = batteryService.findBatteryInformationByDeviceId(device.getDeviceId());
        return batteries.stream().map(BatteryDTO::from).collect(Collectors.toList());
    }
}
