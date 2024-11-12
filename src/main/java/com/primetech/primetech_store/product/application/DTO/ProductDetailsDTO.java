package com.primetech.primetech_store.product.application.DTO;

import com.primetech.primetech_store.product.domain.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDTO {
    private UUID productId;
    private String name;
    private String description;
    private String brand;
    private int stock;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String categoryName;
    private UUID sellerId;

    public ProductDetailsDTO(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.brand = product.getBrand();
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();

        this.categoryName = product.getCategory() != null ? product.getCategory().getCategoryName() : null;
        this.sellerId = product.getUser() != null ? product.getUser().getUserId() : null;
    }
}
