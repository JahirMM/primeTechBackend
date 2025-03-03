package com.primetech.primetech_store.recentProducts.infrastructure.repositories;

import com.primetech.primetech_store.recentProducts.domain.models.RecentProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecentProductRepository extends JpaRepository<RecentProduct, UUID> {
    List<RecentProduct> findByUserIdOrderByVisitDataDesc(UUID userId);
    List<RecentProduct> findByProductProductId(UUID productId);
    boolean existsByProductProductIdAndUserId(UUID productId, UUID userId);
    Optional<RecentProduct> findByProductProductIdAndUserId(UUID productId, UUID userId);
}
