package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.application.exception.UserNotSellerException;
import com.primetech.primetech_store.product.domain.interfaces.ScreenServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class DeleteScreenApplication {
    public final ScreenServiceInterface screenService;
    public final UserServiceInterface userService;
    public final UserRoleAssignmentServiceInterface userRoleAssignmentService;

    @Transactional
    public void deleteScreenApplication(UUID screenId, String email) {
        User user = userService.findUserInformationByEmail(email);

        if (!userRoleAssignmentService.isSeller(user)) {
            throw new UserNotSellerException("The user is not a seller.");
        }
        screenService.deleteScreenByScreenId(screenId);
    }
}
