package com.primetech.primetech_store.config.purchaseAndSellProductConfig;

import com.primetech.primetech_store.common.application.AddPurchaseAndSellProductApplication;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.purchasedProduct.application.AddPurchasedProductApplication;
import com.primetech.primetech_store.soldProduct.application.AddSoldProductApplication;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class PurchaseAndSellProductConfig {
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;
    private final ProductImageServiceInterface productImageService;

    private final AddPurchasedProductApplication addPurchasedProductApplication;
    private final AddSoldProductApplication addSoldProductApplication;

    @Bean
    public AddPurchaseAndSellProductApplication addPurchaseAndSellProductApplication() {
        return new AddPurchaseAndSellProductApplication(
                userService, productService, productImageService,
                addPurchasedProductApplication, addSoldProductApplication
        );
    }
}
