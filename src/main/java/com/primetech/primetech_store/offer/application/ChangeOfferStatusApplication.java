package com.primetech.primetech_store.offer.application;

import com.primetech.primetech_store.common.application.exception.OfferStatusException;
import com.primetech.primetech_store.offer.domain.interfaces.OfferServiceInterface;
import com.primetech.primetech_store.offer.domain.models.Offer;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class ChangeOfferStatusApplication {
    private final OfferServiceInterface offerService;

    @Transactional
    public String changeOfferStatus(UUID offerId, boolean status){
        Offer offer = offerService.findByOfferId(offerId);
        if (offer.isActive() == status) {
            String messageException = status ? "The offer is now active" : "The offer is now deactivated";
            throw  new OfferStatusException(messageException);
        }

        offer.setActive(status);
        offerService.addOffer(offer);
        return status ? "Offer successfully activated" : "Offer successfully deactivated";
    }
}
