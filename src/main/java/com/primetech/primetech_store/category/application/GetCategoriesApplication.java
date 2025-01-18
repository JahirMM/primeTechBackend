package com.primetech.primetech_store.category.application;

import com.primetech.primetech_store.category.domain.interfaces.CategoryServiceInterface;
import com.primetech.primetech_store.category.domain.models.Category;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class GetCategoriesApplication {
    private final CategoryServiceInterface categoryService;

    @Transactional
    public List<Category> getCategories() {
        return categoryService.findCategories();
    }
}
