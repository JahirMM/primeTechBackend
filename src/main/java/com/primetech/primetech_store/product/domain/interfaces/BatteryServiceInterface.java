package com.primetech.primetech_store.product.domain.interfaces;

import com.primetech.primetech_store.product.domain.models.Battery;

import java.util.List;
import java.util.UUID;

public interface BatteryServiceInterface {
    Battery saveBattery(Battery battery);
    boolean batteryExistsForDevice(UUID deviceId);
    List<Battery> findBatteryInformationByDeviceId(UUID deviceId);
}
