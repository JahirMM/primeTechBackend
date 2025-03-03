package com.primetech.primetech_store.soldProduct.infrastructure.repositories;

import com.primetech.primetech_store.soldProduct.domain.models.SoldProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SoldProductRepository extends JpaRepository<SoldProduct, UUID> {
    List<SoldProduct> findByUser_UserId(UUID userId);
}