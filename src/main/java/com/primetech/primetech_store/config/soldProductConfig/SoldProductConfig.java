package com.primetech.primetech_store.config.soldProductConfig;

import com.primetech.primetech_store.soldProduct.application.AddSoldProductApplication;
import com.primetech.primetech_store.soldProduct.domain.interfaces.SoldProductServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SoldProductConfig {
    private final SoldProductServiceInterface soldProductService;

    @Bean
    public AddSoldProductApplication addSoldProductApplication() {
        return new AddSoldProductApplication(
                soldProductService
        );
    }
}
