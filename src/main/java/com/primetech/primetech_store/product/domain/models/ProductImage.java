package com.primetech.primetech_store.product.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Table(name = "product_image", schema = "prime_tech_schema")
public class ProductImage {

    @Id
    @Column(name = "product_image_id", nullable = false)
    private UUID productImageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @Column(name = "img_url", nullable = false)
    private String imgURL;

    @Column(name = "main", nullable = false)
    private boolean main;

    public ProductImage() {
        this.productImageId = UUID.randomUUID();
    }

    public ProductImage(Product product, String imgURL, boolean main) {
        this();
        this.product = product;
        this.imgURL = imgURL;
        this.main = main;
    }
}
