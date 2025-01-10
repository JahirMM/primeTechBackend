package com.primetech.primetech_store.Offer.application;

import com.primetech.primetech_store.Offer.application.DTO.OfferDTO;
import com.primetech.primetech_store.Offer.application.DTO.OfferRequestDTO;
import com.primetech.primetech_store.Offer.domain.interfaces.OfferServiceInterface;
import com.primetech.primetech_store.Offer.domain.models.Offer;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class UpdateOfferApplication {
    private final OfferServiceInterface offerService;

    @Transactional
    public OfferDTO updateOffer(UUID offerId, OfferRequestDTO request) {
        Offer offer = offerService.findByOfferId(offerId);

        offer.setDiscountPercentage(request.getDiscountPercentage());
        offer.setStartDate(request.getStartDate());
        offer.setEndDate(request.getEndDate());

        if (!offer.isActive()) {
            offer.setActive(true);
        }

        Offer offerSave = offerService.addOffer(offer);

        return new OfferDTO(offerSave);
    }
}
