package com.primetech.primetech_store.product.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProductRequestDTO {
    private String name;
    private String description;
    private String brand;
    private int stock;
    private BigDecimal price;
    private String category;
}
