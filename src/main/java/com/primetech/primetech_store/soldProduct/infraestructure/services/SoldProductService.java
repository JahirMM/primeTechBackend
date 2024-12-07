package com.primetech.primetech_store.soldProduct.infraestructure.services;

import com.primetech.primetech_store.soldProduct.domain.interfaces.SoldProductServiceInterface;
import com.primetech.primetech_store.soldProduct.domain.models.SoldProduct;
import com.primetech.primetech_store.soldProduct.infraestructure.repositories.SoldProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SoldProductService implements SoldProductServiceInterface {

    private final SoldProductRepository soldProductRepository;

    @Override
    public SoldProduct saveSoldProduct(SoldProduct soldProduct) {
        return soldProductRepository.save(soldProduct);
    }

    @Override
    public List<SoldProduct> findByUserId(UUID userId) {
        return soldProductRepository.findByUser_UserId(userId);
    }
}
