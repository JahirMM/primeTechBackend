package com.primetech.primetech_store.favoriteProduct.infraestructure.services;

import com.primetech.primetech_store.common.exception.ProductNotFoundException;
import com.primetech.primetech_store.favoriteProduct.domain.interfaces.FavoriteProductServiceInterface;
import com.primetech.primetech_store.favoriteProduct.domain.models.FavoriteProduct;
import com.primetech.primetech_store.favoriteProduct.infraestructure.repositories.FavoriteProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
