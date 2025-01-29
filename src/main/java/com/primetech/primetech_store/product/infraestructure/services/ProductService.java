package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.common.application.exception.ProductNotFoundException;
import com.primetech.primetech_store.product.application.DTO.PriceRangeDTO;
import com.primetech.primetech_store.product.application.DTO.product.ProductDetailsProjectionDTO;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.product.infraestructure.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServiceInterface {
    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProductByProductId(UUID productId) {
        Optional<Product> productOptional = productRepository.findProductByProductId(productId);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }

        return productOptional.get();
    }

    @Override
    public boolean existsProductByProductId(UUID productId) {
        return productRepository.existsByProductId(productId);
    }

    @Override
    public PriceRangeDTO findMinimumAndMaximumPrice() {
        return productRepository.findPriceRange();
    }

    @Override
    public Page<Product> findAllProducts(String name, String brand, UUID categoryId,
                                         UUID sellerId, BigDecimal minPrice, BigDecimal maxPrice,
                                         Double rating, Boolean onSale, Pageable pageable) {
        return productRepository.findByCriteria(name, brand, categoryId, sellerId, minPrice, maxPrice, rating, onSale, pageable);
    }

    @Override
    public Product findByProductIdAndSellerId(UUID productId, UUID sellerId) {
        Optional<Product> productOptional = productRepository.findByProductIdAndUser_UserId(productId, sellerId);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }

        return productOptional.get();
    }

    @Override
    public List<Product> findByUserId(UUID userId) {
        return productRepository.findByUser_UserId(userId);
    }

    @Override
    public void deleteProductByProductId(UUID productId) {
        Optional<Product> productOptional = productRepository.findProductByProductId(productId);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }

        productRepository.deleteByProductId(productId);
    }

    @Override
    public ProductDetailsProjectionDTO findProductDetailsByProductId(UUID productId) {
        return productRepository.findProductDetailsByProductId(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
    }
}
