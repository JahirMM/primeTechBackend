package com.primetech.primetech_store.offer.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOfferResponseDTO {
    private String message;
    private OfferDTO offer;
}
