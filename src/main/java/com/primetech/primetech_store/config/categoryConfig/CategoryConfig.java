package com.primetech.primetech_store.config.categoryConfig;

import com.primetech.primetech_store.category.application.GetCategoriesApplication;
import com.primetech.primetech_store.category.domain.interfaces.CategoryServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class CategoryConfig {
    private final CategoryServiceInterface categoryService;

    @Bean
    public GetCategoriesApplication getCategoriesApplication() {
        return new GetCategoriesApplication(categoryService);
    }
}
