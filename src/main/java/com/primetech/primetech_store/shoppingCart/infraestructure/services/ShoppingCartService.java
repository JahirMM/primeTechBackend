package com.primetech.primetech_store.shoppingCart.infraestructure.services;

import com.primetech.primetech_store.shoppingCart.domain.interfaces.ShoppingCartServiceInterface;
import com.primetech.primetech_store.shoppingCart.domain.models.ShoppingCart;
import com.primetech.primetech_store.shoppingCart.domain.models.ShoppingCartItems;
import com.primetech.primetech_store.shoppingCart.infraestructure.repositories.ShoppingCartItemsRepository;
import com.primetech.primetech_store.shoppingCart.infraestructure.repositories.ShoppingCartRepository;
import com.primetech.primetech_store.common.application.exception.ShoppingCartNotFoundException;
import com.primetech.primetech_store.common.application.exception.UserNotFoundException;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.infraestructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShoppingCartService implements ShoppingCartServiceInterface {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemsRepository shoppingCartItemsRepository;
    private final UserRepository userRepository;

    @Override
    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartItems saveItemInShoppingCart(ShoppingCartItems item) {
        return shoppingCartItemsRepository.save(item);
    }

    @Override
    public ShoppingCart getOrCreateShoppingCart(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return shoppingCartRepository.findByUser_UserId(user.get().getUserId())
                .orElseGet(() -> saveShoppingCart(new ShoppingCart(user.get(), false)));
    }

    @Override
    public ShoppingCartItems findItemInShoppingCart(UUID shoppingCartId, UUID productId) {
        return shoppingCartItemsRepository.findByShoppingCart_ShoppingCartIdAndProduct_ProductId(shoppingCartId, productId)
                .orElse(null);
    }

    @Override
    public ShoppingCart findByUserId(UUID userId) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findByUser_UserId(userId);
        if (shoppingCartOptional.isEmpty()) {
            throw new ShoppingCartNotFoundException("Shopping cart not found");
        }
        return shoppingCartOptional.get();
    }

    @Override
    public List<ShoppingCartItems> findItemsByShoppingCartId(UUID shoppingCartId) {
        return shoppingCartItemsRepository.findByShoppingCart_ShoppingCartId(shoppingCartId);
    }

    @Override
    public boolean exitsByShoppingCartId(UUID shoppingCartId) {
        return shoppingCartRepository.existsByShoppingCartId(shoppingCartId);
    }

    @Override
    public void deleteProductInShoppingCart(ShoppingCartItems shoppingCartItem) {
        shoppingCartItemsRepository.delete(shoppingCartItem);
    }
}
