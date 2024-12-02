package com.primetech.primetech_store.config.shoppingCartConfig;

import com.primetech.primetech_store.ShoppingCart.application.AddProductToShoppingCartApplication;
import com.primetech.primetech_store.ShoppingCart.application.DeleteProductFromShoppingCartApplication;
import com.primetech.primetech_store.ShoppingCart.application.GetShoppingCartApplication;
import com.primetech.primetech_store.ShoppingCart.application.UpdateShoppingCartItemApplication;
import com.primetech.primetech_store.ShoppingCart.domain.interfaces.ShoppingCartServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ShoppingCartConfig {
    private final ShoppingCartServiceInterface shoppingCartService;
    private final ProductServiceInterface productService;
    private final ProductImageServiceInterface productImageService;
    private final UserServiceInterface userService;

    @Bean
    public AddProductToShoppingCartApplication addProductToShoppingCartApplication() {
        return new AddProductToShoppingCartApplication(
                shoppingCartService, productService,
                productImageService
        );
    }

    @Bean
    public  GetShoppingCartApplication getShoppingCartApplication() {
        return new GetShoppingCartApplication(
                shoppingCartService, userService,
                productImageService
        );
    }

    @Bean
    public UpdateShoppingCartItemApplication updateShoppingCartItemApplication() {
        return new UpdateShoppingCartItemApplication(shoppingCartService);
    }

    @Bean
    public DeleteProductFromShoppingCartApplication deleteProductFromShoppingCartApplication() {
        return new DeleteProductFromShoppingCartApplication(userService, shoppingCartService);
    }
}
