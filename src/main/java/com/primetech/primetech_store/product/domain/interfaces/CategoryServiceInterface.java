package com.primetech.primetech_store.product.domain.interfaces;

import com.primetech.primetech_store.product.domain.models.Category;

public interface CategoryServiceInterface {
    Category findCategoryByCategoryName(String categoryName);
}
