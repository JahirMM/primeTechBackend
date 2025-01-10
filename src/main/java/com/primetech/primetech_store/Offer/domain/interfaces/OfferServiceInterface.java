package com.primetech.primetech_store.Offer.domain.interfaces;

import com.primetech.primetech_store.Offer.domain.models.Offer;
import com.primetech.primetech_store.product.domain.models.Product;

import java.util.UUID;

public interface OfferServiceInterface {
    Offer addOffer(Offer offer);
    boolean existsByProduct(Product product);
    Offer findByProductId(UUID productId);
    Offer findByOfferId(UUID offerId);
}
