package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.common.exception.MobileDeviceNotAllowedException;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.DeviceType;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.product.infraestructure.repositories.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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

    @Override
    public Device findDeviceByMultipleDeviceTypes(UUID productId, UUID deviceMobileTypeId, UUID deviceLaptopTypeId) {
        List<UUID> deviceTypeIds = Arrays.asList(deviceMobileTypeId, deviceLaptopTypeId);
        Optional<Device> deviceOptional = deviceRepository.findByProduct_ProductIdAndDeviceType_DeviceTypeIdIn(productId, deviceTypeIds);

        if (deviceOptional.isEmpty()) {
            throw new MobileDeviceNotAllowedException("Product does not allow additional information for the specified device types.");
        }

        return deviceOptional.get();
    }
}
