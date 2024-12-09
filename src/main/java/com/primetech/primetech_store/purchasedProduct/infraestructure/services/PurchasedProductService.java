package com.primetech.primetech_store.purchasedProduct.infraestructure.services;

import com.primetech.primetech_store.purchasedProduct.domain.interfaces.PurchasedProductServiceInterface;
import com.primetech.primetech_store.purchasedProduct.domain.models.PurchasedProduct;
import com.primetech.primetech_store.purchasedProduct.infraestructure.repositories.PurchasedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchasedProductService implements PurchasedProductServiceInterface {
    private final PurchasedProductRepository purchaseHistoryRepository;

    @Override
    public PurchasedProduct savePurchasedProduct(PurchasedProduct purchasedProduct) {
        return purchaseHistoryRepository.save(purchasedProduct);
    }

    @Override
    public List<PurchasedProduct> findByUserId(UUID userId) {
        return purchaseHistoryRepository.findByUser_UserId(userId);
    }

    @Override
    public List<PurchasedProduct> findByOrderIdAndUserId(UUID orderId, UUID userId) {
        return purchaseHistoryRepository.findByOrder_OrderIdAndUser_UserId(orderId, userId);
    }
}
