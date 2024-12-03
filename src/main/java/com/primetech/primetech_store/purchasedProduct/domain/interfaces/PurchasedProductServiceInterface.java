package com.primetech.primetech_store.purchasedProduct.domain.interfaces;

import com.primetech.primetech_store.purchasedProduct.domain.models.PurchasedProduct;

import java.util.List;
import java.util.UUID;

public interface PurchasedProductServiceInterface {
    PurchasedProduct savePurchasedProduct(PurchasedProduct purchasedProduct);
    List<PurchasedProduct> findByUserId(UUID userId);
}
