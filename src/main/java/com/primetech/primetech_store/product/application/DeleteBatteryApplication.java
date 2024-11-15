package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.UserNotSellerException;
import com.primetech.primetech_store.product.domain.interfaces.BatteryServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class DeleteBatteryApplication {
    public BatteryServiceInterface batteryService;
    public UserServiceInterface userService;
    public UserRoleAssignmentServiceInterface userRoleAssignmentService;

    @Transactional
    public void deleteCameraApplication(UUID batteryId, String email) {
        User user = userService.findUserInformationByEmail(email);

        if (!userRoleAssignmentService.isSeller(user)) {
            throw new UserNotSellerException("The user is not a seller.");
        }
        batteryService.deleteBatteryByBatteryId(batteryId);
    }
}
