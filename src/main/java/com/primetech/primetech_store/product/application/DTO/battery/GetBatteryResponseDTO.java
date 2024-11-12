package com.primetech.primetech_store.product.application.DTO.battery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBatteryResponseDTO {
    private List<BatteryDTO> batteries;
}
