package com.primetech.primetech_store.recentProducts.domain.interfaces;

import com.primetech.primetech_store.recentProducts.domain.models.RecentProduct;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecentProductServiceInterface {
    List<RecentProduct> getRecentProducts(UUID userId);
    RecentProduct saveRecentProduct(RecentProduct recentProduct);
    void deleteRecentProduct(RecentProduct recentProduct);
    boolean existsByProductIdAndUserId(UUID productId, UUID userId);
    RecentProduct findByProductIdAndUserId(UUID productId, UUID userId);
}
