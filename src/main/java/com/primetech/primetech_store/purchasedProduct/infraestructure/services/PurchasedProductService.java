package com.primetech.primetech_store.purchasedProduct.infraestructure.services;

import com.primetech.primetech_store.purchasedProduct.domain.interfaces.PurchasedProductServiceInterface;
import com.primetech.primetech_store.purchasedProduct.domain.models.PurchasedProduct;
import com.primetech.primetech_store.purchasedProduct.infraestructure.repositories.PurchasedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchasedProductService implements PurchasedProductServiceInterface {
    private final PurchasedProductRepository purchaseHistoryRepository;

    @Override
    public PurchasedProduct savePurchasedProduct(PurchasedProduct purchasedProduct) {
        return purchaseHistoryRepository.save(purchasedProduct);
    }
}
