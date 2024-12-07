package com.primetech.primetech_store.soldProduct.domain.interfaces;

import com.primetech.primetech_store.soldProduct.domain.models.SoldProduct;

import java.util.List;
import java.util.UUID;

public interface SoldProductServiceInterface {
    SoldProduct saveSoldProduct(SoldProduct soldProduct);
    List<SoldProduct> findByUserId(UUID userId);
}
