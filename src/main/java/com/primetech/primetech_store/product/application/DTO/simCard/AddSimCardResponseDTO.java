package com.primetech.primetech_store.product.application.DTO.simCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSimCardResponseDTO {
    private String message;
    private SimCardDTO simCard;
}
