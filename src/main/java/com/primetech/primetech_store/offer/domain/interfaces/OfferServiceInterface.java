package com.primetech.primetech_store.offer.domain.interfaces;

import com.primetech.primetech_store.offer.domain.models.Offer;
import com.primetech.primetech_store.product.domain.models.Product;

import java.util.UUID;

public interface OfferServiceInterface {
    Offer addOffer(Offer offer);
    boolean existsByProduct(Product product);
    Offer findByProductId(UUID productId);
    Offer findByOfferId(UUID offerId);
}
