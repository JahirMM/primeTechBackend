package com.primetech.primetech_store.Offer.infraestructure.services;

import com.primetech.primetech_store.Offer.domain.interfaces.OfferServiceInterface;
import com.primetech.primetech_store.Offer.domain.models.Offer;
import com.primetech.primetech_store.Offer.infraestructure.repositories.OfferRepository;
import com.primetech.primetech_store.common.application.exception.OfferNotFoundException;
import com.primetech.primetech_store.product.domain.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OfferServices implements OfferServiceInterface {
    private final OfferRepository offerRepository;

    @Override
    public Offer addOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public boolean existsByProduct(Product product) {
        return offerRepository.existsByProduct(product);
    }

    @Override
    public Offer findByProductId(UUID productId) {
        return offerRepository.findByProductProductId(productId).orElseThrow(() -> new OfferNotFoundException("Offer not found"));
    }

    @Override
    public Offer findByOfferId(UUID offerId) {
        return offerRepository.findByOfferId(offerId).orElseThrow(() -> new OfferNotFoundException("Offer not found"));
    }
}
