package com.primetech.primetech_store.recentProducts.domain.models;

import com.primetech.primetech_store.product.domain.models.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "recent_product", schema = "prime_tech_schema")
public class RecentProduct {
    @Id
    @Column(name = "recent_product_id", nullable = false)
    private UUID recentProductId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "visit_date", nullable = false)
    private LocalDateTime visitData;

    public RecentProduct() {
        this.recentProductId = UUID.randomUUID();
    }

    public RecentProduct(UUID userId, Product product, LocalDateTime visitData) {
        this();
        this.userId = userId;
        this.product = product;
        this.visitData = visitData;
    }
}