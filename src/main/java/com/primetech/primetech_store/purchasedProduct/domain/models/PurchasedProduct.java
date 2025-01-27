package com.primetech.primetech_store.purchasedProduct.domain.models;

import com.primetech.primetech_store.order.domain.models.Order;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.user.domain.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "purchased_product", schema = "prime_tech_schema")
@Data
public class PurchasedProduct {
    @Id
    @Column(name = "purchase_id", nullable = false)
    private UUID purchaseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    private Order order;

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

    @Column(name = "seller_id")
    private UUID sellerId;

    @Column(name = "seller_name", nullable = false)
    private String sellerName;

    @Column(name = "seller_email", nullable = false)
    private String sellerEmail;

    @Column(name = "purchase_quantity", nullable = false)
    private int purchaseQuantity;

    public PurchasedProduct() {
        this.purchaseId = UUID.randomUUID();
    }

    public PurchasedProduct(
            User user, Order order, Product product,
            String productImg, int purchaseQuantity) {
        this();
        this.user = user;
        this.order = order;
        this.productId = product.getProductId();
        this.productName = product.getName();
        this.productDescription = product.getDescription();
        this.productPrice = product.getPrice();
        this.productImg = productImg;
        this.sellerId = product.getUser().getUserId();
        this.sellerName = product.getUser().getFirstName() + " "  + product.getUser().getPaternalSurname();
        this.sellerEmail = product.getUser().getEmail();
        this.purchaseQuantity = purchaseQuantity;
    }
}
