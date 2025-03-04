package com.primetech.primetech_store.review.domain.models;

import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.user.domain.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "review", schema = "prime_tech_schema")
public class Review {
    @Id
    @Column(name = "review_id", nullable = false)
    private UUID reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "comment", length = 800)
    private String comment;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Review() {
        this.reviewId = UUID.randomUUID();
    }

    public Review(Product product, User user, Double rating, String comment) {
        this();
        this.product = product;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
