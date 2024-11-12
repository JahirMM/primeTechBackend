package com.primetech.primetech_store.product.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceRangeDTO {
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
