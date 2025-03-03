package com.primetech.primetech_store.category.infrastructure.services;

import com.primetech.primetech_store.common.application.exception.CategoryNotFoundException;
import com.primetech.primetech_store.category.domain.interfaces.CategoryServiceInterface;
import com.primetech.primetech_store.category.domain.models.Category;
import com.primetech.primetech_store.category.infrastructure.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }
}
