package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.ProductNotFoundException;
import com.primetech.primetech_store.common.exception.ScreenAlreadyExistsException;
import com.primetech.primetech_store.common.exception.UserNotSellerException;
import com.primetech.primetech_store.product.application.DTO.screen.ScreenRequestDTO;
import com.primetech.primetech_store.product.application.DTO.screen.ScreenDTO;
import com.primetech.primetech_store.product.domain.interfaces.*;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.DeviceType;
import com.primetech.primetech_store.product.domain.models.Screen;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class AddScreenApplication {
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;
    private final DeviceTypeServiceInterface deviceTypeService;
    private final DeviceServiceInterface deviceService;
    private final ScreenServiceInterface screenService;

    @Transactional
    public ScreenDTO addScreenApplication(ScreenRequestDTO request, String email, UUID productId) {
        User user = userService.findUserInformationByEmail(email);

        if (!userRoleAssignmentService.isSeller(user)) {
            throw new UserNotSellerException("The user is not a seller.");
        }

        if (!productService.existsProductByProductId(productId)) {
            throw new ProductNotFoundException("Product not found");
        }

        DeviceType deviceMobileType = deviceTypeService.findDeviceTypeByTypeName("mobile");
        DeviceType deviceLaptopType = deviceTypeService.findDeviceTypeByTypeName("laptop");

        Device device = deviceService.findDeviceByMultipleDeviceTypes(productId, deviceMobileType.getDeviceTypeId(), deviceLaptopType.getDeviceTypeId());

        if (screenService.screenExistsForDevice(device.getDeviceId())) {
            throw new ScreenAlreadyExistsException("Screen already exists for device");
        }

        Screen screen = createScreen(device, request);
        Screen saveScreen = screenService.saveScreen(screen);

        return ScreenDTO.from(saveScreen);
    }

    private Screen createScreen(Device device, ScreenRequestDTO request) {
        return new Screen(
                device,
                request.getResolution(),
                request.getPixelDensity(),
                request.getRefreshRate(),
                request.getScreenType(),
                request.getScreenSize()
        );
    }
}
