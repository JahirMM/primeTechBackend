package com.primetech.primetech_store.recentProducts.infrastructure.services;

import com.primetech.primetech_store.common.application.exception.ProductNotFoundException;
import com.primetech.primetech_store.recentProducts.domain.interfaces.RecentProductServiceInterface;
import com.primetech.primetech_store.recentProducts.domain.models.RecentProduct;
import com.primetech.primetech_store.recentProducts.infrastructure.repositories.RecentProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecentProductService implements RecentProductServiceInterface {
    private final RecentProductRepository recentProductRepository;

    @Override
    public List<RecentProduct> getRecentProducts(UUID userId) {
        return recentProductRepository.findByUserIdOrderByVisitDataDesc(userId);
    }

    @Override
    public List<RecentProduct> getRecentProductsByProductId(UUID productId) {
        return recentProductRepository.findByProductProductId(productId);
    }

    @Override
    public RecentProduct saveRecentProduct(RecentProduct recentProduct) {
        return recentProductRepository.save(recentProduct);
    }

    @Override
    public void deleteRecentProduct(RecentProduct recentProduct) {
        recentProductRepository.delete(recentProduct);
    }

    @Override
    public boolean existsByProductIdAndUserId(UUID productId, UUID userId) {
        return recentProductRepository.existsByProductProductIdAndUserId(productId, userId);
    }

    @Override
    public RecentProduct findByProductIdAndUserId(UUID productId, UUID userId) {
        return recentProductRepository.findByProductProductIdAndUserId(productId, userId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

}
