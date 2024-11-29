package com.primetech.primetech_store.ShoppingCart.domain.models;

import com.primetech.primetech_store.user.domain.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "shopping_cart", schema = "prime_tech_schema")
@Data
public class ShoppingCart {
    @Id
    @Column(name = "shopping_cart_id", nullable = false)
    private UUID shoppingCartId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;

    public ShoppingCart() {
        this.shoppingCartId = UUID.randomUUID();
    }

    public ShoppingCart(User user, boolean isCompleted) {
        this();
        this.user = user;
        this.isCompleted = isCompleted;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
