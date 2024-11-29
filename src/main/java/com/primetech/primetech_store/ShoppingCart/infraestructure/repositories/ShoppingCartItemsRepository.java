package com.primetech.primetech_store.ShoppingCart.infraestructure.repositories;

import com.primetech.primetech_store.ShoppingCart.domain.models.ShoppingCartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShoppingCartItemsRepository extends JpaRepository<ShoppingCartItems, UUID> {
    Optional<ShoppingCartItems> findByShoppingCart_ShoppingCartIdAndProduct_ProductId(UUID shoppingCartId, UUID productId);
}
