package com.primetech.primetech_store.product.domain.interfaces;

import com.primetech.primetech_store.product.domain.models.DeviceType;

import java.util.Optional;

public interface DeviceTypeServiceInterface {
    DeviceType findDeviceTypeByTypeName(String typeName);
}
