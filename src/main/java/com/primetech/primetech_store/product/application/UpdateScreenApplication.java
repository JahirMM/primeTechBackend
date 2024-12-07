package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.application.exception.UserNotSellerException;
import com.primetech.primetech_store.product.application.DTO.screen.ScreenDTO;
import com.primetech.primetech_store.product.application.DTO.screen.ScreenRequestDTO;
import com.primetech.primetech_store.product.domain.interfaces.ScreenServiceInterface;
import com.primetech.primetech_store.product.domain.models.Screen;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class UpdateScreenApplication {
    private final ScreenServiceInterface screenService;
    private final UserServiceInterface userService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;

    @Transactional
    public ScreenDTO updateScreenApplication(String email, UUID screenId, ScreenRequestDTO request) {
        if (!userRoleAssignmentService.isSeller(userService.findUserInformationByEmail(email))) {
            throw new UserNotSellerException("The user is not a seller.");
        }

        Screen screen = screenService.findScreenByScreenId(screenId);
        screen.setResolution(request.getResolution());
        screen.setPixelDensity(request.getPixelDensity());
        screen.setRefreshRate(request.getRefreshRate());
        screen.setScreenType(request.getScreenType());
        screen.setScreenSize(request.getScreenSize());

        Screen saveScreen = screenService.saveScreen(screen);

        return ScreenDTO.from(saveScreen);
    }
}
