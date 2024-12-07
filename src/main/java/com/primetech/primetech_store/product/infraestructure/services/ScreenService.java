package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.common.application.exception.ProductNotFoundException;
import com.primetech.primetech_store.product.domain.interfaces.ScreenServiceInterface;
import com.primetech.primetech_store.product.domain.models.Screen;
import com.primetech.primetech_store.product.infraestructure.repositories.ScreenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public List<Screen> findScreenInformationByDeviceId(UUID deviceId) {
        return screenRepository.findByDevice_DeviceId(deviceId);
    }

    @Override
    public Screen findScreenByScreenId(UUID screenId) {
        Optional<Screen> screenOptional = screenRepository.findByScreenId(screenId);

        if (screenOptional.isEmpty()) {
            throw new ProductNotFoundException("Screen not found");
        }


        return screenOptional.get();
    }

    @Override
    public void deleteScreenByScreenId(UUID screenId) {
        Optional<Screen> screenOptional = screenRepository.findByScreenId(screenId);

        if (screenOptional.isEmpty()) {
            throw new ProductNotFoundException("Screen not found");
        }
        screenRepository.deleteByScreenId(screenId);
    }
}
