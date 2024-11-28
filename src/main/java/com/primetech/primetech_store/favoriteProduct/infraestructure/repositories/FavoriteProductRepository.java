package com.primetech.primetech_store.favoriteProduct.infraestructure.repositories;

import com.primetech.primetech_store.favoriteProduct.domain.models.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, UUID> {
}