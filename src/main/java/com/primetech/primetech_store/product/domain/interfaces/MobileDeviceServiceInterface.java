package com.primetech.primetech_store.product.domain.interfaces;

import com.primetech.primetech_store.product.domain.models.MobileDevice;

import java.util.List;
import java.util.UUID;

public interface MobileDeviceServiceInterface {
    MobileDevice saveMobileDevice(MobileDevice mobileDevice);
    MobileDevice findMobileDeviceByMobileDeviceId(UUID mobileDeviceId);
    List<MobileDevice> findMobileDeviceInformationByDeviceId(UUID deviceId);
}
