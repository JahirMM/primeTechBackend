package com.primetech.primetech_store.product.domain.models;

import com.primetech.primetech_store.category.domain.models.Category;
import com.primetech.primetech_store.user.domain.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "product", schema = "prime_tech_schema")
public class Product {
    @Id
    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "price", scale = 3,nullable = false)
    private BigDecimal price;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Product() {
        this.productId = UUID.randomUUID();
    }

    public Product(String name, String description, String brand, int stock, BigDecimal price) {
        this();
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.stock = stock;
        this.price = price;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

