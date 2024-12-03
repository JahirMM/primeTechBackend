package com.primetech.primetech_store.purchasedProduct.infraestructure.repositories;

import com.primetech.primetech_store.purchasedProduct.domain.models.PurchasedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PurchasedProductRepository extends JpaRepository<PurchasedProduct, UUID> {
    List<PurchasedProduct> findByUser_UserId(UUID userId);
}
