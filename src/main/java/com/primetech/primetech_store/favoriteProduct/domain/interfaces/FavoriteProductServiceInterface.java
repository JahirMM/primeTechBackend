package com.primetech.primetech_store.favoriteProduct.domain.interfaces;

import com.primetech.primetech_store.favoriteProduct.domain.models.FavoriteProduct;

import java.util.List;
import java.util.UUID;

public interface FavoriteProductServiceInterface {
    FavoriteProduct addFavoriteProduct(FavoriteProduct favoriteProduct);
    List<FavoriteProduct> getFavoriteProductByUserId(UUID userId);
    void deleteFavoriteProduct(FavoriteProduct favoriteProduct);
    FavoriteProduct findByFavoriteProductIdAndUserId(UUID favoriteProductId, UUID userId);
    boolean existsByProductIdAndUserId(UUID productId, UUID userId);
    List<FavoriteProduct> findByProductId(UUID productId);
}
