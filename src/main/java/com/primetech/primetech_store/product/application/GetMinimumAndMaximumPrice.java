package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.product.application.DTO.PriceRangeDTO;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetMinimumAndMaximumPrice {
    private final ProductServiceInterface productService;

    public PriceRangeDTO getMinimumAndMaximumPrice() {
        return productService.findMinimumAndMaximumPrice();
    }
}
