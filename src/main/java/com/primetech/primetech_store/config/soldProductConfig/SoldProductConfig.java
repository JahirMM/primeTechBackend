package com.primetech.primetech_store.config.soldProductConfig;

import com.primetech.primetech_store.soldProduct.application.AddSoldProductApplication;
import com.primetech.primetech_store.soldProduct.application.GetSoldProductsApplication;
import com.primetech.primetech_store.soldProduct.domain.interfaces.SoldProductServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SoldProductConfig {
    private final SoldProductServiceInterface soldProductService;
    private final UserServiceInterface userService;
    @Bean
    public AddSoldProductApplication addSoldProductApplication() {
        return new AddSoldProductApplication(
                soldProductService
        );
    }

    @Bean
    public GetSoldProductsApplication getSoldProductsApplication() {
        return new GetSoldProductsApplication(
                soldProductService, userService
        );
    }
}
