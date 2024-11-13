package com.primetech.primetech_store.product.application.DTO.battery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateResponseDTO {
    private String message;
    private BatteryDTO battery;
}
