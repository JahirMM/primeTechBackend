package com.primetech.primetech_store.product.application.DTO.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private UUID productId;
    private UUID sellerId;
    private String name;
    private String description;
    private String brand;
    private int stock;
    private BigDecimal price;
    private String category;
    private String deviceType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
