package com.primetech.primetech_store.Offer.domain.interfaces;

import com.primetech.primetech_store.Offer.domain.models.Offer;
import com.primetech.primetech_store.product.domain.models.Product;

public interface OfferServiceInterface {
    Offer addOffer(Offer offer);
    boolean existsByProduct(Product product);
}
