package com.primetech.primetech_store.filter.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterResponseDTO {
    private UUID categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private List<String> brands;
}
