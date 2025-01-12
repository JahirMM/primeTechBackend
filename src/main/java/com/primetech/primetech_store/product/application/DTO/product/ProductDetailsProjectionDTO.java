package com.primetech.primetech_store.product.application.DTO.product;

import com.primetech.primetech_store.product.domain.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsProjectionDTO {
    private Product product;
    private String image;
    private String deviceType;
    private Double averageRating;
    private BigDecimal discountPercentage;
    private boolean activeOffer;
}
