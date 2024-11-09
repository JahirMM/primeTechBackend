package com.primetech.primetech_store.product.domain.interfaces;

import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.DeviceType;
import com.primetech.primetech_store.product.domain.models.Product;

import java.util.UUID;

public interface DeviceServiceInterface {
    Device saveDevice(Product product, DeviceType deviceType);
    Device findDevice(UUID productId, UUID deviceTypeID);
    Device findDeviceByMultipleDeviceTypes(UUID productId, UUID deviceMobileTypeId, UUID deviceLaptopTypeId);
}
