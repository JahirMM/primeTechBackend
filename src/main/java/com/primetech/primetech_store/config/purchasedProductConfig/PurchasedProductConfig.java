package com.primetech.primetech_store.config.purchasedProductConfig;

import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
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
    private final ProductServiceInterface productService;
    private final ProductImageServiceInterface productImageService;

    @Bean
    public AddPurchasedProductApplication addPurchasedProductApplication() {
        return new AddPurchasedProductApplication(
                purchasedProductService, userService,
                productService, productImageService
        );
    }

    @Bean
    public GetPurchasedProductsApplication getPurchasedProductsApplication() {
        return new GetPurchasedProductsApplication(
                purchasedProductService, userService
        );
    }
}
