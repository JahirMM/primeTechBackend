package com.primetech.primetech_store.favoriteProduct.application;

import com.primetech.primetech_store.favoriteProduct.domain.interfaces.FavoriteProductServiceInterface;
import com.primetech.primetech_store.favoriteProduct.domain.models.FavoriteProduct;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class DeleteFavoriteProductApplication {
    private final FavoriteProductServiceInterface favoriteProductService;
    private final UserServiceInterface userService;

    @Transactional
    public void deleteFavoriteProduct(String email, UUID favoriteProductId) {
        User user = userService.findUserInformationByEmail(email);

        FavoriteProduct favoriteProduct = favoriteProductService.findByFavoriteProductIdAndUserId(favoriteProductId, user.getUserId());
        favoriteProductService.deleteFavoriteProduct(favoriteProduct);
    }
}
