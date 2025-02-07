package com.primetech.primetech_store.product.domain.interfaces;

import com.primetech.primetech_store.filter.application.DTO.MinMaxPriceDTO;
import com.primetech.primetech_store.filter.application.DTO.ProductFilterDataDTO;
import com.primetech.primetech_store.product.application.DTO.PriceRangeDTO;
import com.primetech.primetech_store.product.application.DTO.product.ProductDetailsProjectionDTO;
import com.primetech.primetech_store.product.domain.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductServiceInterface {
    Product saveProduct(Product product);
    Product findProductByProductId(UUID productId);
    Product findByProductIdAndSellerId(UUID productId, UUID sellerId);
    List<Product> findByUserId(UUID userId);
    Page<Product> findAllProducts(String name, String brand, UUID categoryId,
                                  UUID sellerId, BigDecimal minPrice, BigDecimal maxPrice,
                                  Double raiting, Boolean onSale, Pageable pageable);
    ProductDetailsProjectionDTO findProductDetailsByProductId(UUID productId);
    PriceRangeDTO findMinimumAndMaximumPrice();
    boolean existsProductByProductId(UUID productId);
    void deleteProductByProductId(UUID productId);
    Optional<MinMaxPriceDTO> findMinMaxPrice(
            UUID categoryId,
            String brand,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Double minRating
    );
    List<String> findBrands(
            UUID categoryId,
            String brand,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Double minRating
    );
    long countProductByUserId(UUID userId);
}
