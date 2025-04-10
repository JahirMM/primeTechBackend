package com.primetech.primetech_store.product.application.DTO.mobileDevice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMobileDeviceResponseDTO {
    private String message;
    private MobileDeviceDTO mobileDevice;
}
