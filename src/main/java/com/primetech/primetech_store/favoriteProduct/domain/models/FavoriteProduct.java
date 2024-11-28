package com.primetech.primetech_store.favoriteProduct.domain.models;

import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.user.domain.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "favorite_product", schema = "prime_tech_schema")
public class FavoriteProduct {

    @Id
    @Column(name = "favorite_product_id", nullable = false)
    private UUID favoriteProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    public FavoriteProduct() {
        this.favoriteProductId = UUID.randomUUID();
    }

    public FavoriteProduct(Product product, User user) {
        this();
        this.product = product;
        this.user = user;
    }
}
