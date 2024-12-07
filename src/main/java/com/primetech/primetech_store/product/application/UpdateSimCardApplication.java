package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.application.exception.UserNotSellerException;
import com.primetech.primetech_store.product.application.DTO.simCard.SimCardDTO;
import com.primetech.primetech_store.product.application.DTO.simCard.SimCardRequestDTO;
import com.primetech.primetech_store.product.domain.interfaces.SimCardServiceInterface;
import com.primetech.primetech_store.product.domain.models.SimCard;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class UpdateSimCardApplication {
    private final SimCardServiceInterface simCardService;
    private final UserServiceInterface userService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;

    @Transactional
    public SimCardDTO updateSimCardApplication(String email, UUID simCardIs, SimCardRequestDTO request) {
        if (!userRoleAssignmentService.isSeller(userService.findUserInformationByEmail(email))) {
            throw new UserNotSellerException("The user is not a seller.");
        }

        SimCard simCard = simCardService.findSimCardBySimCardId(simCardIs);
        simCard.setDualSim(request.isDualSim());
        simCard.setEsim(request.isEsim());
        simCard.setSimType(request.getSimType());

        SimCard saveSimCard = simCardService.saveSimCard(simCard);

        return SimCardDTO.from(saveSimCard);
    }
}
