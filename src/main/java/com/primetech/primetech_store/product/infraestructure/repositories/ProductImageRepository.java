package com.primetech.primetech_store.product.infraestructure.repositories;

import com.primetech.primetech_store.product.domain.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductImageRepository extends JpaRepository<ProductImage, UUID> {
    List<ProductImage> findByProduct_ProductId(UUID productId);
    Optional<ProductImage> findByProductImageId(UUID productImageId);
    int countByProduct_ProductId(UUID productId);
    boolean existsByProduct_ProductIdAndMainTrue(UUID productId);
    Optional<ProductImage> findByProduct_ProductIdAndMainTrue(UUID productId);
}
