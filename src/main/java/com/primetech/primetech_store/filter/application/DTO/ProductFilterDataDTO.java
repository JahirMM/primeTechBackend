package com.primetech.primetech_store.filter.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilterDataDTO {
    String brand;
    BigDecimal minPrice;
    BigDecimal maxPrice;
}
