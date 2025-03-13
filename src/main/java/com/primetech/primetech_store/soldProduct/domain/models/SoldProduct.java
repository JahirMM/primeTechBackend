package com.primetech.primetech_store.soldProduct.domain.models;

import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.user.domain.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sold_product", schema = "prime_tech_schema")
@Data
public class SoldProduct {
    @Id
    @Column(name = "sold_id", nullable = false)
    private UUID soldId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_description", nullable = false, length = 700)
    private String productDescription;

    @Column(name = "product_price", nullable = false)
    private BigDecimal productPrice;

    @Column(name = "product_img")
    private String productImg;

    @Column(name = "purchase_quantity", nullable = false)
    private int purchaseQuantity;

    @Column(name = "sale_date", nullable = false)
    private LocalDateTime saleDate;

    public SoldProduct() {
        this.soldId = UUID.randomUUID();
    }

    public SoldProduct(
            Product product,
            String productImg, int purchaseQuantity) {
        this();
        this.user = product.getUser();
        this.productId = product.getProductId();
        this.productName = product.getName();
        this.productDescription = product.getDescription();
        this.productPrice = product.getPrice();
        this.productImg = productImg;
        this.purchaseQuantity = purchaseQuantity;
    }

    @PrePersist
    protected void onCreate() {
        this.saleDate = LocalDateTime.now();
    }
}
