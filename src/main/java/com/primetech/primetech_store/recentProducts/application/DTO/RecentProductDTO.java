package com.primetech.primetech_store.recentProducts.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentProductDTO {
    private UUID productId;
    private String name;
    private String brand;
    private BigDecimal price;
    private Double averageRating;
    private Boolean activeOffer;
    private BigDecimal discountPercentage;
    private String imageUrl;
}
