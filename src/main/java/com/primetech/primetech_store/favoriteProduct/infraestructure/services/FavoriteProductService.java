package com.primetech.primetech_store.favoriteProduct.infraestructure.services;

import com.primetech.primetech_store.favoriteProduct.domain.interfaces.FavoriteProductServiceInterface;
import com.primetech.primetech_store.favoriteProduct.domain.models.FavoriteProduct;
import com.primetech.primetech_store.favoriteProduct.infraestructure.repositories.FavoriteProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoriteProductService implements FavoriteProductServiceInterface {
    private final FavoriteProductRepository favoriteProductRepository;

    @Override
    public FavoriteProduct addFavoriteProduct(FavoriteProduct favoriteProduct) {
        return favoriteProductRepository.save(favoriteProduct);
    }
}
