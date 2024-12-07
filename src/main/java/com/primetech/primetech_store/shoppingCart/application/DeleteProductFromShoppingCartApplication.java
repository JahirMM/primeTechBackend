package com.primetech.primetech_store.shoppingCart.application;

import com.primetech.primetech_store.shoppingCart.domain.interfaces.ShoppingCartServiceInterface;
import com.primetech.primetech_store.shoppingCart.domain.models.ShoppingCart;
import com.primetech.primetech_store.shoppingCart.domain.models.ShoppingCartItems;
import com.primetech.primetech_store.common.exception.ProductNotFoundException;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class DeleteProductFromShoppingCartApplication {
    private final UserServiceInterface userService;
    private final ShoppingCartServiceInterface shoppingCartService;


    @Transactional
    public void deleteProduct(String email, UUID productId) {
        User user = userService.findUserInformationByEmail(email);

        ShoppingCart shoppingCart = shoppingCartService.findByUserId(user.getUserId());
        ShoppingCartItems shoppingCartItem = shoppingCartService.findItemInShoppingCart(shoppingCart.getShoppingCartId(), productId);

        if (shoppingCartItem == null) {
            throw new ProductNotFoundException("Product not found in the shopping cart");
        }

        shoppingCartService.deleteProductInShoppingCart(shoppingCartItem);
    }
}
