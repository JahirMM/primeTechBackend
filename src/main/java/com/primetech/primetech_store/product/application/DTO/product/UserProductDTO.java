package com.primetech.primetech_store.product.application.DTO.product;

import com.primetech.primetech_store.product.domain.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserProductDTO {
    private UUID productId;
    private String imageUrl;
    private String name;
    private String description;
    private String brand;
    private int stock;
    private BigDecimal price;
    private String category;
    private String deviceType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserProductDTO(Product product, String image, String deviceType) {
        this.productId = product.getProductId();
        this.imageUrl = image;
        this.name = product.getName();
        this.description = product.getDescription();
        this.brand = product.getBrand();
        this.stock = product.getStock();
        this.price = product.getPrice().setScale(3, RoundingMode.HALF_UP);
        this.deviceType = deviceType;
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
    }
}
