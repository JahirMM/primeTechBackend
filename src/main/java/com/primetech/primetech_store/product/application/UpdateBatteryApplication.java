package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.application.exception.UserNotSellerException;
import com.primetech.primetech_store.product.application.DTO.battery.BatteryDTO;
import com.primetech.primetech_store.product.application.DTO.battery.BatteryRequestDTO;
import com.primetech.primetech_store.product.domain.interfaces.BatteryServiceInterface;
import com.primetech.primetech_store.product.domain.models.Battery;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class UpdateBatteryApplication {
    private final BatteryServiceInterface batteryService;
    private final UserServiceInterface userService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;

    @Transactional
    public BatteryDTO updateBatteryApplication(String email, UUID batteryId, BatteryRequestDTO request) {
        if (!userRoleAssignmentService.isSeller(userService.findUserInformationByEmail(email))) {
            throw new UserNotSellerException("The user is not a seller.");
        }

        Battery battery = batteryService.findBatteryByBatteryId(batteryId);
        battery.setCapacity(request.getCapacity());
        battery.setType(request.getType());
        battery.setWirelessCharging(request.getWirelessCharging());
        battery.setFastCharging(request.getFastCharging());
        battery.setMaxBatteryDuration(request.getMaxBatteryDuration());

        Battery saveBattery = batteryService.saveBattery(battery);

        return BatteryDTO.from(saveBattery);
    }
}
