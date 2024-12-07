package com.primetech.primetech_store.shoppingCart.domain.interfaces;

import com.primetech.primetech_store.shoppingCart.domain.models.ShoppingCart;
import com.primetech.primetech_store.shoppingCart.domain.models.ShoppingCartItems;

import java.util.List;
import java.util.UUID;

public interface ShoppingCartServiceInterface {
    ShoppingCart saveShoppingCart(ShoppingCart shoppingCart);
    ShoppingCartItems saveItemInShoppingCart(ShoppingCartItems item);
    ShoppingCart getOrCreateShoppingCart(String email);
    ShoppingCartItems findItemInShoppingCart(UUID shoppingCartId, UUID productId);
    ShoppingCart findByUserId(UUID userId);
    List<ShoppingCartItems> findItemsByShoppingCartId(UUID shoppingCartId);
    boolean exitsByShoppingCartId(UUID shoppingCartId);
    void deleteProductInShoppingCart(ShoppingCartItems shoppingCartItem);
}
