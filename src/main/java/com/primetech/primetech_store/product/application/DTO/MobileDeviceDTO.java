package com.primetech.primetech_store.product.application.DTO;

import com.primetech.primetech_store.product.domain.models.MobileDevice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileDeviceDTO {
    private int internalMemory;
    private String internalMemoryType;
    private int ram;
    private String color;
    private String processor;
    private String operatingSystem;
    private String ipRating;
    private boolean splashResistant;
    private boolean dustResistant;
    private boolean waterResistant;

    public static MobileDeviceDTO from(MobileDevice mobileDevice) {
        return new MobileDeviceDTO(
                mobileDevice.getInternalMemory(),
                mobileDevice.getInternalMemoryType(),
                mobileDevice.getRam(),
                mobileDevice.getColor(),
                mobileDevice.getProcessor(),
                mobileDevice.getOperatingSystem(),
                mobileDevice.getIpRating(),
                mobileDevice.isSplashResistant(),
                mobileDevice.isDustResistant(),
                mobileDevice.isWaterResistant()
        );
    }
}

