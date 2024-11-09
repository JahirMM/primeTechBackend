package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.common.exception.MobileDeviceNotAllowedException;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.DeviceType;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.product.infraestructure.repositories.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DeviceService implements DeviceServiceInterface {
    private final DeviceRepository deviceRepository;

    @Override
    public Device saveDevice(Product product, DeviceType deviceType) {
        Device device = new Device(product, deviceType);
        return deviceRepository.save(device);
    }

    @Override
    public Device findDevice(UUID productId, UUID deviceTypeID) {
        Optional<Device> deviceOptional = deviceRepository.findByProduct_ProductIdAndDeviceType_DeviceTypeId(productId, deviceTypeID);
        if (deviceOptional.isEmpty()) {
            throw new MobileDeviceNotAllowedException("Product is not allowed additional information in mobile_device");
        }
        return deviceOptional.get();
    }
}
