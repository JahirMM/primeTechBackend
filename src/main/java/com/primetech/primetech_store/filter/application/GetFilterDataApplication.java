package com.primetech.primetech_store.filter.application;

import com.primetech.primetech_store.filter.application.DTO.FilterResponseDTO;
import com.primetech.primetech_store.filter.application.DTO.MinMaxPriceDTO;
import com.primetech.primetech_store.filter.application.DTO.ProductFilterDataDTO;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class GetFilterDataApplication {
    private final ProductServiceInterface productService;

    @Transactional
    public FilterResponseDTO getFilterData(UUID categoryId,
                                           String brand,
                                           BigDecimal minPrice,
                                           BigDecimal maxPrice,
                                           Double minRating) {
        // Obtener lista de marcas
        List<String> brandNames = productService.findBrands(categoryId, brand, minPrice, maxPrice, minRating);

        // Obtener precios mínimo y máximo
        MinMaxPriceDTO minMaxPrice = productService.findMinMaxPrice(categoryId, brand, minPrice, maxPrice, minRating)
                .orElse(new MinMaxPriceDTO(BigDecimal.ZERO, BigDecimal.ZERO));

        return new FilterResponseDTO(categoryId, minMaxPrice.getMinPrice(), minMaxPrice.getMaxPrice(), brandNames);
    }

}
