package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.UserNotSellerException;
import com.primetech.primetech_store.product.application.DTO.mobileDevice.MobileDeviceDTO;
import com.primetech.primetech_store.product.application.DTO.mobileDevice.MobileDeviceRequestDTO;
import com.primetech.primetech_store.product.domain.interfaces.MobileDeviceServiceInterface;
import com.primetech.primetech_store.product.domain.models.MobileDevice;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class UpdateMobileDeviceApplication {
    private final MobileDeviceServiceInterface mobileDeviceService;
    private final UserServiceInterface userService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;

    @Transactional
    public MobileDeviceDTO updateMobileDeviceApplication(String email, UUID mobileDeviceId, MobileDeviceRequestDTO request) {
        if (!userRoleAssignmentService.isSeller(userService.findUserInformationByEmail(email))) {
            throw new UserNotSellerException("The user is not a seller.");
        }

        MobileDevice mobileDevice = mobileDeviceService.findMobileDeviceByMobileDeviceId(mobileDeviceId);
        mobileDevice.setInternalMemory(request.getInternalMemory());
        mobileDevice.setInternalMemoryType(request.getInternalMemoryType());
        mobileDevice.setRam(request.getRam());
        mobileDevice.setColor(request.getColor());
        mobileDevice.setProcessor(request.getProcessor());
        mobileDevice.setOperatingSystem(request.getOperatingSystem());
        mobileDevice.setIpRating(request.getIpRating());
        mobileDevice.setSplashResistant(request.isSplashResistant());
        mobileDevice.setDustResistant(request.isDustResistant());
        mobileDevice.setWaterResistant(request.isWaterResistant());

        MobileDevice saveMobileDevice = mobileDeviceService.saveMobileDevice(mobileDevice);

        return MobileDeviceDTO.from(saveMobileDevice);
    }
}
