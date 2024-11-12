package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.MobeliDeviceAlreadyExistsException;
import com.primetech.primetech_store.common.exception.UserNotSellerException;
import com.primetech.primetech_store.product.application.DTO.simCard.AddSimCardRequestDTO;
import com.primetech.primetech_store.product.application.DTO.simCard.SimCardDTO;
import com.primetech.primetech_store.product.domain.interfaces.MobileDeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.SimCardServiceInterface;
import com.primetech.primetech_store.product.domain.models.MobileDevice;
import com.primetech.primetech_store.product.domain.models.SimCard;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class AddSimCardApplication {
    private final UserServiceInterface userService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;
    private final SimCardServiceInterface simCardService;
    private final MobileDeviceServiceInterface mobileDeviceService;

    @Transactional
    public SimCardDTO addSimCardApplication(AddSimCardRequestDTO request, UUID mobileDeviceId, String email) {
        User user = userService.findUserInformationByEmail(email);

        if (!userRoleAssignmentService.isSeller(user)) {
            throw new UserNotSellerException("The user is not a seller.");
        }

        MobileDevice mobileDevice = mobileDeviceService.findMobileDeviceByMobileDeviceId(mobileDeviceId);

        if (simCardService.existsSimCardByMobileDeviceId(mobileDevice.getMobileDeviceId())) {
            throw new MobeliDeviceAlreadyExistsException("Mobile device already exists");
        }

        SimCard simCard = createSimCard(mobileDevice, request);
        SimCard saveSimCard = simCardService.saveSimCard(simCard);

        return SimCardDTO.from(saveSimCard);
    }

    private SimCard createSimCard(MobileDevice mobileDevice, AddSimCardRequestDTO request) {
        return new SimCard(
                mobileDevice,
                request.isDualSim(),
                request.getSimSlots(),
                request.isEsim(),
                request.getSimType()
        );
    }
}
