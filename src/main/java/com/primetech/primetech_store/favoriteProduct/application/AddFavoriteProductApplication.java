package com.primetech.primetech_store.favoriteProduct.application;

import com.primetech.primetech_store.favoriteProduct.domain.interfaces.FavoriteProductServiceInterface;
import com.primetech.primetech_store.favoriteProduct.domain.models.FavoriteProduct;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class AddFavoriteProductApplication {
    private final FavoriteProductServiceInterface favoriteProductService;
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;

    @Transactional
    public FavoriteProduct AddFavoriteProduct(String email, UUID productId) {
        User user = getUser(email);
        Product product = productService.findProductByProductId(productId);

        FavoriteProduct favoriteProduct = new FavoriteProduct(product, user);

        return favoriteProductService.addFavoriteProduct(favoriteProduct);
    }

    private User getUser(String email) {
        return userService.findUserInformationByEmail(email);
    }
}