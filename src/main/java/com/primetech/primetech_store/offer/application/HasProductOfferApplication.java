package com.primetech.primetech_store.offer.application;

import com.primetech.primetech_store.offer.domain.interfaces.OfferServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class HasProductOfferApplication {
    private final OfferServiceInterface offerService;

    @Transactional
    public boolean hasProductOffer(UUID productId) {
        return offerService.existsByProductId(productId);
    }
}
