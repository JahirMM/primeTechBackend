package com.primetech.primetech_store.product.application.DTO.screen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateScreenResponseDTO {
    private String message;
    private ScreenDTO screen;
}
