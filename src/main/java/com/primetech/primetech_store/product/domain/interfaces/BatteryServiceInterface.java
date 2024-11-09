package com.primetech.primetech_store.product.domain.interfaces;

import com.primetech.primetech_store.product.domain.models.Battery;

import java.util.UUID;

public interface BatteryServiceInterface {
    Battery saveBattery(Battery battery);
    boolean batteryExistsForDevice(UUID deviceId);
}
