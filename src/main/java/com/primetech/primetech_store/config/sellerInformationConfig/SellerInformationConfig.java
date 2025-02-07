package com.primetech.primetech_store.config.sellerInformationConfig;

import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.sellerInformation.application.GetSellerInformationApplication;
import com.primetech.primetech_store.user.domain.interfaces.UserImageServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.infraestructure.services.UserRoleAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SellerInformationConfig {
    private final UserRoleAssignmentService userRoleAssignment;
    private final UserImageServiceInterface userImageService;
    private final ProductServiceInterface productService;
    private final UserServiceInterface userService;

    @Bean
    public GetSellerInformationApplication getSellerInformationApplication() {
        return new GetSellerInformationApplication(
                userRoleAssignment, userImageService,
                productService, userService
        );
    }
}
