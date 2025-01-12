package com.primetech.primetech_store.product.application.DTO.product;

import com.primetech.primetech_store.product.domain.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductDetailsDTO {
    private UUID productId;
    private UUID sellerId;
    private String image;
    private String name;
    private String description;
    private String brand;
    private int stock;
    private BigDecimal price;
    private String categoryName;
    private String deviceType;
    private Double averageRating;
    private BigDecimal discountPercentage;
    private boolean activeOffer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductDetailsDTO(Product product, String image, String deviceType,
                             Double averageRating, BigDecimal discountPercentage,
                             boolean activeOffer) {
        this.productId = product.getProductId();
        this.sellerId = product.getUser() != null ? product.getUser().getUserId() : null;
        this.image = image;
        this.name = product.getName();
        this.description = product.getDescription();
        this.brand = product.getBrand();
        this.stock = product.getStock();
        this.price = product.getPrice().setScale(3, RoundingMode.HALF_UP);
        this.categoryName = product.getCategory() != null ? product.getCategory().getCategoryName() : null;
        this.deviceType = deviceType;
        this.averageRating = averageRating;
        this.discountPercentage = discountPercentage;
        this.activeOffer = activeOffer;
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
    }
}
