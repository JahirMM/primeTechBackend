package com.primetech.primetech_store.config.productConfig;

import com.primetech.primetech_store.product.application.AddCameraApplication;
import com.primetech.primetech_store.product.application.AddMobileDeviceApplication;
import com.primetech.primetech_store.product.application.AddProductApplication;
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

    @Bean
    public AddProductApplication addProductApplication(){
        return new AddProductApplication(
                productService, userService,
                categoryService, userRoleAssignmentService,
                deviceService, deviceTypeService
        );}

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
}
