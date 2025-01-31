package com.primetech.primetech_store.config.filterConfig;

import com.primetech.primetech_store.filter.application.GetFilterDataApplication;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class FilterConfig {
    private final ProductServiceInterface productService;

    @Bean
    public GetFilterDataApplication  getFilterDataApplication() {
        return new GetFilterDataApplication(productService);
    }
}
