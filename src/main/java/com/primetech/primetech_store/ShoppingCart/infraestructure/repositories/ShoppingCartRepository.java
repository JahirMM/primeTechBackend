package com.primetech.primetech_store.ShoppingCart.infraestructure.repositories;

import com.primetech.primetech_store.ShoppingCart.domain.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {
    Optional<ShoppingCart> findByUser_UserId(UUID userId);
}
