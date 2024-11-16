package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.common.exception.MobileDeviceNotFoundException;
import com.primetech.primetech_store.product.domain.interfaces.MobileDeviceServiceInterface;
import com.primetech.primetech_store.product.domain.models.MobileDevice;
import com.primetech.primetech_store.product.infraestructure.repositories.MobileDeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MobileDeviceService implements MobileDeviceServiceInterface {
    private final MobileDeviceRepository mobileDeviceRepository;

    @Override
    public MobileDevice saveMobileDevice(MobileDevice mobileDevice) {
        return mobileDeviceRepository.save(mobileDevice);
    }

    @Override
    public MobileDevice findMobileDeviceByMobileDeviceId(UUID mobileDeviceId) {
        Optional<MobileDevice> mobileDeviceOptional = mobileDeviceRepository.findByMobileDeviceId(mobileDeviceId);

        if (mobileDeviceOptional.isEmpty()) {
            throw new MobileDeviceNotFoundException("Mobile device not found");
        }

        return mobileDeviceOptional.get();
    }

    @Override
    public List<MobileDevice> findMobileDeviceInformationByDeviceId(UUID deviceId) {
        return mobileDeviceRepository.findByDevice_DeviceId(deviceId);
    }

    @Override
    public void deleteMobileDeviceByMobileDeviceId(UUID mobileDeviceId) {
        Optional<MobileDevice> mobileDeviceOptional = mobileDeviceRepository.findByMobileDeviceId(mobileDeviceId);

        if (mobileDeviceOptional.isEmpty()) {
            throw new MobileDeviceNotFoundException("Mobile device not found");
        }

        mobileDeviceRepository.deleteByMobileDeviceId(mobileDeviceId);
    }
}
