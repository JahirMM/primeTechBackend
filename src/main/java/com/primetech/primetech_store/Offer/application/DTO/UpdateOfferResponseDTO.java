package com.primetech.primetech_store.Offer.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOfferResponseDTO {
    private String message;
    private OfferDTO offer;
}
