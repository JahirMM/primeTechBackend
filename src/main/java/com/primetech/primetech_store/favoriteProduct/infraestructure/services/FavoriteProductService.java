package com.primetech.primetech_store.favoriteProduct.infraestructure.services;

import com.primetech.primetech_store.common.application.exception.ProductNotFoundException;
import com.primetech.primetech_store.favoriteProduct.domain.interfaces.FavoriteProductServiceInterface;
import com.primetech.primetech_store.favoriteProduct.domain.models.FavoriteProduct;
import com.primetech.primetech_store.favoriteProduct.infraestructure.repositories.FavoriteProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FavoriteProductService implements FavoriteProductServiceInterface {
    private final FavoriteProductRepository favoriteProductRepository;

    @Override
    public FavoriteProduct addFavoriteProduct(FavoriteProduct favoriteProduct) {
        return favoriteProductRepository.save(favoriteProduct);
    }

    @Override
    public List<FavoriteProduct> getFavoriteProductByUserId(UUID userId) {
        List<FavoriteProduct> favoriteProducts = favoriteProductRepository.findByUser_UserId(userId);
        if (favoriteProducts.isEmpty()) {
            throw new ProductNotFoundException("Favorite products not found");
        }
        return favoriteProducts;
    }

    @Override
    public void deleteFavoriteProduct(FavoriteProduct favoriteProduct) {
        favoriteProductRepository.delete(favoriteProduct);
    }

    @Override
    public FavoriteProduct findByFavoriteProductIdAndUserId(UUID favoriteProductId, UUID userId) {
        Optional<FavoriteProduct> favoriteProductOptional = favoriteProductRepository.findByFavoriteProductIdAndUser_UserId(favoriteProductId, userId);
        if (favoriteProductOptional.isEmpty()) {
            throw new ProductNotFoundException("Favorite product associated with the user not found");
        }
        return favoriteProductOptional.get();
    }

    @Override
    public boolean existsByProductIdAndUserId(UUID productId, UUID userId) {
        return favoriteProductRepository.existsByProduct_ProductIdAndUser_userId(productId, userId);
    }

    @Override
    public List<FavoriteProduct> findByProductId(UUID productId) {
        return favoriteProductRepository.findByProduct_ProductId(productId);
    }
}
