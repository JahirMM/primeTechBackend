package com.primetech.primetech_store.product.application.DTO.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String name;
    private String description;
    private String brand;
    private int stock;
    private BigDecimal price;
    private String category;
    private String deviceType;
    private LocalDateTime createdAt;
}
