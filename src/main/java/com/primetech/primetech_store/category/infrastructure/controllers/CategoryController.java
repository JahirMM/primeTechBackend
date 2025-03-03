package com.primetech.primetech_store.category.infrastructure.controllers;

import com.primetech.primetech_store.category.application.GetCategoriesApplication;
import com.primetech.primetech_store.category.domain.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prime-tech/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final GetCategoriesApplication getCategoriesApplication;

    @GetMapping("")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = getCategoriesApplication.getCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(categories);
        }
        return ResponseEntity.ok(categories);
    }
}
