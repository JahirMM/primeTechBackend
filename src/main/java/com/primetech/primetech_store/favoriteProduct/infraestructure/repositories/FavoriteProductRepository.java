package com.primetech.primetech_store.favoriteProduct.infraestructure.repositories;

import com.primetech.primetech_store.favoriteProduct.domain.models.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, UUID> {
    List<FavoriteProduct> findByUser_UserId(UUID userId);
    Optional<FavoriteProduct> findByFavoriteProductIdAndUser_UserId(UUID favoriteProductId, UUID userId);
    boolean existsByProduct_ProductIdAndUser_userId(UUID productId, UUID userId);
    List<FavoriteProduct> findByProduct_ProductId(UUID productId);
}