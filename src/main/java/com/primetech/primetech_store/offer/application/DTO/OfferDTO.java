package com.primetech.primetech_store.offer.application.DTO;

import com.primetech.primetech_store.offer.domain.models.Offer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class OfferDTO {
    private UUID offerId;
    private UUID productId;
    private BigDecimal discountPercentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isActive;

    public OfferDTO (Offer offer) {
        this.offerId = offer.getOfferId();
        this.productId = offer.getProduct().getProductId();
        this.discountPercentage = offer.getDiscountPercentage();
        this.startDate = offer.getStartDate();
        this.endDate = offer.getEndDate();
        this.isActive = offer.isActive();
    }
}
