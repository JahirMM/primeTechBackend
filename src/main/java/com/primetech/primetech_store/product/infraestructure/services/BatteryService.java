package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.product.domain.interfaces.BatteryServiceInterface;
import com.primetech.primetech_store.product.domain.models.Battery;
import com.primetech.primetech_store.product.infraestructure.repositories.BatteryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BatteryService implements BatteryServiceInterface {
    private final BatteryRepository batteryRepository;

    @Override
    public Battery saveBattery(Battery battery) {
        return batteryRepository.save(battery);
    }

    @Override
    public boolean batteryExistsForDevice(UUID deviceId) {
        return batteryRepository.existsByDevice_DeviceId(deviceId);
    }
}