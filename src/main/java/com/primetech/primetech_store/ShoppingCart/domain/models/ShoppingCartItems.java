package com.primetech.primetech_store.ShoppingCart.domain.models;

import com.primetech.primetech_store.product.domain.models.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "shopping_cart_items", schema = "prime_tech_schema")
@Data
public class ShoppingCartItems {
    @Id
    @Column(name = "shopping_cart_item_id", nullable = false)
    private UUID shoppingCartItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "shopping_cart_id", nullable = false)
    private ShoppingCart shoppingCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public ShoppingCartItems() {
        this.shoppingCartItemId = UUID.randomUUID();
    }

    public ShoppingCartItems(ShoppingCart shoppingCart, Product product, int quantity) {
        this();
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.quantity = quantity;
    }
}
