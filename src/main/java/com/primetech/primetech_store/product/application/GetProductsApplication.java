package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.product.application.DTO.GetProductsResponseDTO;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GetProductsApplication {
    private final ProductServiceInterface productService;

    @Transactional
    public List<GetProductsResponseDTO> getProductsApplication() {
        List<Product> products = productService.findAllProducts();
        return products.stream()
                .map(GetProductsResponseDTO::new)
                .collect(Collectors.toList());
    }
}
