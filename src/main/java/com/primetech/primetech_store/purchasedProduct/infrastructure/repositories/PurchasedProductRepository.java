package com.primetech.primetech_store.purchasedProduct.infrastructure.repositories;

import com.primetech.primetech_store.purchasedProduct.domain.models.PurchasedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PurchasedProductRepository extends JpaRepository<PurchasedProduct, UUID> {
    List<PurchasedProduct> findByUser_UserId(UUID userId);
    List<PurchasedProduct> findByOrder_OrderIdAndUser_UserId(UUID orderId, UUID userId);
    boolean existsByProductIdAndUser_UserId(UUID productId, UUID userId);
}
