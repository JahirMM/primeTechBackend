package com.primetech.primetech_store.config.productConfig;

import com.primetech.primetech_store.product.application.AddProductApplication;
import com.primetech.primetech_store.product.domain.interfaces.CategoryServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.DeviceTypeServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
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

    @Bean
    public AddProductApplication addProductApplication(){
        return new AddProductApplication(
                productService, userService,
                categoryService, userRoleAssignmentService,
                deviceService, deviceTypeService
        );}
}
