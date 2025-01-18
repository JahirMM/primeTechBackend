package com.primetech.primetech_store.category.domain.interfaces;

import com.primetech.primetech_store.category.domain.models.Category;

import java.util.List;

public interface CategoryServiceInterface {
    Category findCategoryByCategoryName(String categoryName);
    List<Category> findCategories();
}
