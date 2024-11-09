package com.primetech.primetech_store.product.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "category", schema = "prime_tech_schema")
public class Category {
    @Id
    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    public Category() {
        this.categoryId = UUID.randomUUID();
    }

    public  Category(String categoryName) {
        this();
        this.categoryName = categoryName;
    }
}
