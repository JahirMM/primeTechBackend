package com.primetech.primetech_store.config.recentProductConfing;

import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.recentProducts.application.AddRecentProductApplication;
import com.primetech.primetech_store.recentProducts.application.GetRecentProductApplication;
import com.primetech.primetech_store.recentProducts.domain.interfaces.RecentProductServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RecentProductConfing {
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;
    private final RecentProductServiceInterface recentProductService;

    @Bean
    public AddRecentProductApplication addRecentProductApplication() {
        return new AddRecentProductApplication(
                userService, productService,
                recentProductService);
    }

    @Bean
    public GetRecentProductApplication getRecentProductApplication() {
        return new GetRecentProductApplication(
                userService, productService,
                recentProductService
        );
    }
}
