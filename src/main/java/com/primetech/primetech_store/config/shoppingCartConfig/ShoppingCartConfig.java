package com.primetech.primetech_store.config.shoppingCartConfig;

import com.primetech.primetech_store.ShoppingCart.application.AddProductToShoppingCartApplication;
import com.primetech.primetech_store.ShoppingCart.domain.interfaces.ShoppingCartServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ShoppingCartConfig {
    private final ShoppingCartServiceInterface shoppingCartService;
    private final ProductServiceInterface productService;
    private final ProductImageServiceInterface productImageService;

    @Bean
    public AddProductToShoppingCartApplication addProductToShoppingCartApplication() {
        return new AddProductToShoppingCartApplication(
                shoppingCartService, productService,
                productImageService
        );
    }
}
