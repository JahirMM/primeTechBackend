package com.primetech.primetech_store.Offer.application;

import com.primetech.primetech_store.Offer.application.DTO.OfferDTO;
import com.primetech.primetech_store.Offer.domain.interfaces.OfferServiceInterface;
import com.primetech.primetech_store.Offer.domain.models.Offer;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class GetOfferApplication {
    private final OfferServiceInterface offerService;

    @Transactional
    public OfferDTO getOffer(UUID productId) {
        Offer offer = offerService.findByProductId(productId);
        return new OfferDTO(offer);
    }
}
