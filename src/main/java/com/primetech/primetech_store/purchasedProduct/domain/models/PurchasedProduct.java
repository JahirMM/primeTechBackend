package com.primetech.primetech_store.purchasedProduct.domain.models;

import com.primetech.primetech_store.user.domain.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "purchased_product", schema = "prime_tech_schema")
@Data
public class PurchasedProduct {
    @Id
    @Column(name = "purchase_id", nullable = false)
    private UUID purchaseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_description", nullable = false)
    private String productDescription;

    @Column(name = "product_price", nullable = false)
    private BigDecimal productPrice;

    @Column(name = "product_img")
    private String productImg;

    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime purchaseDate;

    @Column(name = "purchase_quantity", nullable = false)
    private int purchaseQuantity;

    public PurchasedProduct() {
        this.purchaseId = UUID.randomUUID();
    }

    public PurchasedProduct(User user, UUID productId, String productName, String productDescription, BigDecimal productPrice, String productImg, int purchaseQuantity) {
        this();
        this.user = user;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productImg = productImg;
        this.purchaseQuantity = purchaseQuantity;
    }

    @PrePersist
    protected void onCreate() {
        this.purchaseDate = LocalDateTime.now();
    }
}
