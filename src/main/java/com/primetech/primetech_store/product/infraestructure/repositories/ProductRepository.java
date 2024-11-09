package com.primetech.primetech_store.product.infraestructure.repositories;

import com.primetech.primetech_store.product.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findProductByProductId(UUID productId);
    boolean existsByProductId(UUID productId);
}