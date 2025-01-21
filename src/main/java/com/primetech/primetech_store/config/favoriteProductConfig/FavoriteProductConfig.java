package com.primetech.primetech_store.config.favoriteProductConfig;

import com.primetech.primetech_store.favoriteProduct.application.AddFavoriteProductApplication;
import com.primetech.primetech_store.favoriteProduct.application.DeleteFavoriteProductApplication;
import com.primetech.primetech_store.favoriteProduct.application.GetFavoriteProductsApplication;
import com.primetech.primetech_store.favoriteProduct.domain.interfaces.FavoriteProductServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class FavoriteProductConfig {
    private final FavoriteProductServiceInterface favoriteProductService;
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;

    @Bean
    public AddFavoriteProductApplication addFavoriteProductApplication(){
        return new AddFavoriteProductApplication(
                favoriteProductService, userService,
                productService
        );
    }

    @Bean
    public GetFavoriteProductsApplication getFavoriteProductsApplication(){
        return new GetFavoriteProductsApplication(
                favoriteProductService, userService, productService
        );
    }

    @Bean
    public DeleteFavoriteProductApplication deleteFavoriteProductApplication() {
        return new DeleteFavoriteProductApplication(
                favoriteProductService, userService
        );
    }
}
