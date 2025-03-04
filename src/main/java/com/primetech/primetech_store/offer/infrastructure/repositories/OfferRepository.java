package com.primetech.primetech_store.offer.infrastructure.repositories;

import com.primetech.primetech_store.offer.domain.models.Offer;
import com.primetech.primetech_store.product.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, UUID> {
    boolean existsByProduct(Product product);
    boolean existsByProductProductId(UUID product);
    Optional<Offer> findByProductProductId(UUID productId);
    Optional<Offer> findByOfferId(UUID offerId);
}
