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
    private String name;
    private String description;
    private String brand;
    private int stock;
    private BigDecimal price;

    public FavoriteProductDetailsDTO(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.brand = product.getBrand();
        this.stock = product.getStock();
        this.price = product.getPrice().setScale(3, RoundingMode.HALF_UP);
    }
}
