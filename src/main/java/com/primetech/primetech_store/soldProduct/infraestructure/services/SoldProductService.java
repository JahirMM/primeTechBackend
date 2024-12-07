package com.primetech.primetech_store.soldProduct.infraestructure.services;

import com.primetech.primetech_store.soldProduct.domain.interfaces.SoldProductServiceInterface;
import com.primetech.primetech_store.soldProduct.domain.models.SoldProduct;
import com.primetech.primetech_store.soldProduct.infraestructure.repositories.SoldProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoldProductService implements SoldProductServiceInterface {

    private final SoldProductRepository soldProductRepository;

    @Override
    public SoldProduct saveSoldProduct(SoldProduct soldProduct) {
        return soldProductRepository.save(soldProduct);
    }
}
