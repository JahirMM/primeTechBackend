package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.product.domain.interfaces.MobileDeviceServiceInterface;
import com.primetech.primetech_store.product.domain.models.MobileDevice;
import com.primetech.primetech_store.product.infraestructure.repositories.MobileDeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MobileDeviceService implements MobileDeviceServiceInterface {
    private final MobileDeviceRepository mobileDeviceRepository;

    @Override
    public MobileDevice saveMobileDevice(MobileDevice mobileDevice) {
        return mobileDeviceRepository.save(mobileDevice);
    }
}
