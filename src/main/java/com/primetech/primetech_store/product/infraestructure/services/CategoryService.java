package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.common.exception.CategoryNotFoundException;
import com.primetech.primetech_store.product.domain.interfaces.CategoryServiceInterface;
import com.primetech.primetech_store.product.domain.models.Category;
import com.primetech.primetech_store.product.infraestructure.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService implements CategoryServiceInterface {
    private final CategoryRepository categoryRepository;
    @Override
    public Category findCategoryByCategoryName(String categoryName) {
        Optional<Category> categoryOptional = categoryRepository.findByCategoryName(categoryName);
        if (categoryOptional.isEmpty()) {
            throw new CategoryNotFoundException("Category not found");
        }
        return categoryOptional.get();
    }
}
