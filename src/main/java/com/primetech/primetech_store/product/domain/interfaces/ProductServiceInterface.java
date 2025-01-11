package com.primetech.primetech_store.product.domain.interfaces;

import com.primetech.primetech_store.product.application.DTO.PriceRangeDTO;
import com.primetech.primetech_store.product.domain.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductServiceInterface {
    Product saveProduct(Product product);
    Product findProductByProductId(UUID productId);
    boolean existsProductByProductId(UUID productId);
    PriceRangeDTO findMinimumAndMaximumPrice();
    Page<Product> findAllProducts(String name, String brand, UUID categoryId,
                                  UUID sellerId, BigDecimal minPrice, BigDecimal maxPrice,
                                  Double raiting, Boolean onSale, Pageable pageable);
    Product findByProductIdAndSellerId(UUID productId, UUID sellerId);
    void deleteProductByProductId(UUID productId);
}
