package com.primetech.primetech_store.config.productConfig;

import com.primetech.primetech_store.product.application.*;
import com.primetech.primetech_store.product.domain.interfaces.*;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ProductConfig {
    private final ProductServiceInterface productService;
    private final UserServiceInterface userService;
    private final CategoryServiceInterface categoryService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;
    private final DeviceServiceInterface deviceService;
    private final DeviceTypeServiceInterface deviceTypeService;
    private final MobileDeviceServiceInterface mobileDeviceService;
    private final CameraServiceInterface cameraService;
    private final ScreenServiceInterface screenService;
    private final BatteryServiceInterface batteryService;
    private final LaptopServiceInterface laptopService;
    private final SimCardServiceInterface simCardService;

    @Bean
    public AddProductApplication addProductApplication(){
        return new AddProductApplication(
                productService, userService,
                categoryService, userRoleAssignmentService,
                deviceService, deviceTypeService
        );}

    @Bean
    public GetProductApplication getProductApplication() {
        return new GetProductApplication(
                productService, deviceService
        );
    }

    @Bean
    public AddMobileDeviceApplication addMobileDeviceApplication(){
        return new AddMobileDeviceApplication(
                deviceTypeService, deviceService,
                productService, userService, userRoleAssignmentService,
                mobileDeviceService
        );
    }

    @Bean
    public AddCameraApplication addCameraApplication(){
        return new AddCameraApplication(
                userService, productService,
                userRoleAssignmentService, deviceTypeService,
                deviceService, cameraService
        );
    }

    @Bean
    public AddScreenApplication addScreenApplication() {
        return new AddScreenApplication(
                userService, productService,
                userRoleAssignmentService, deviceTypeService,
                deviceService, screenService
        );
    }

    @Bean
    public AddBatteryApplication addBatteryApplication() {
        return new AddBatteryApplication(
                userService, productService,
                userRoleAssignmentService, deviceTypeService,
                deviceService, batteryService
        );
    }

    @Bean
    public AddLaptopApplication addLaptopApplication() {
        return new AddLaptopApplication(
                deviceTypeService, deviceService,
                productService, userService, userRoleAssignmentService,
                laptopService
        );
    }

    @Bean
    public AddSimCardApplication addSimCardApplication() {
        return new AddSimCardApplication(userService, userRoleAssignmentService, simCardService, mobileDeviceService);
    }
}
