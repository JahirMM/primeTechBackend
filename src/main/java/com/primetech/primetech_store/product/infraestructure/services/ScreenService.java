package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.product.domain.interfaces.ScreenServiceInterface;
import com.primetech.primetech_store.product.domain.models.Screen;
import com.primetech.primetech_store.product.infraestructure.repositories.ScreenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ScreenService implements ScreenServiceInterface {
    private final ScreenRepository screenRepository;

    @Override
    public Screen saveScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    @Override
    public boolean screenExistsForDevice(UUID deviceId) {
        return screenRepository.existsByDevice_DeviceId(deviceId);
    }
}
