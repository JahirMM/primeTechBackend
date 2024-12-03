package com.primetech.primetech_store.purchasedProduct.domain.interfaces;

import com.primetech.primetech_store.purchasedProduct.domain.models.PurchasedProduct;

public interface PurchasedProductServiceInterface {
    PurchasedProduct savePurchasedProduct(PurchasedProduct purchasedProduct);
}
