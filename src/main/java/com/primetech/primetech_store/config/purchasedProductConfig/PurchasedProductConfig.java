package com.primetech.primetech_store.config.purchasedProductConfig;

import com.primetech.primetech_store.purchasedProduct.application.AddPurchasedProductApplication;
import com.primetech.primetech_store.purchasedProduct.application.GetPurchasedProductsApplication;
import com.primetech.primetech_store.purchasedProduct.domain.interfaces.PurchasedProductServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class PurchasedProductConfig {
    private final PurchasedProductServiceInterface purchasedProductService;
    private final UserServiceInterface userService;

    @Bean
    public AddPurchasedProductApplication addPurchasedProductApplication() {
        return new AddPurchasedProductApplication(
                purchasedProductService
        );
    }

    @Bean
    public GetPurchasedProductsApplication getPurchasedProductsApplication() {
        return new GetPurchasedProductsApplication(
                purchasedProductService, userService
        );
    }
}
