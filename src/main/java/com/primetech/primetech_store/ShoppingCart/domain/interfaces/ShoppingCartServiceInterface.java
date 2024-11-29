package com.primetech.primetech_store.ShoppingCart.domain.interfaces;

import com.primetech.primetech_store.ShoppingCart.domain.models.ShoppingCart;
import com.primetech.primetech_store.ShoppingCart.domain.models.ShoppingCartItems;

import java.util.UUID;

public interface ShoppingCartServiceInterface {
    ShoppingCart saveShoppingCart(ShoppingCart shoppingCart);
    ShoppingCartItems saveItemInShoppingCart(ShoppingCartItems item);
    ShoppingCart getOrCreateShoppingCart(String email);
    ShoppingCartItems findItemInShoppingCart(UUID shoppingCartId, UUID productId);
}
