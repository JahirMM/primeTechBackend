package com.primetech.primetech_store.favoriteProduct.application.DTO;

import com.primetech.primetech_store.product.domain.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Data
@NoArgsConstructor
public class FavoriteProductDetailsDTO {
    private UUID productId;
    private UUID favoriteProductId;
    private String imgUrl;
    private String name;
    private String description;
    private String brand;
    private int stock;
    private BigDecimal price;
    private Double averageRating;
    private boolean activeOffer;
    private BigDecimal discountPercentage;

    public FavoriteProductDetailsDTO(Product product, UUID favoriteProductId,
                                     String imgUrl, Double averageRating,
                                     boolean activeOffer, BigDecimal discountPercentage) {
        this.productId = product.getProductId();
        this.favoriteProductId = favoriteProductId;
        this.imgUrl = imgUrl;
        this.name = product.getName();
        this.description = product.getDescription();
        this.brand = product.getBrand();
        this.stock = product.getStock();
        this.price = product.getPrice().setScale(3, RoundingMode.HALF_UP);
        this.averageRating = averageRating;
        this.activeOffer = activeOffer;
        this.discountPercentage = discountPercentage;
    }
}
