package com.primetech.primetech_store.product.application.DTO.mobileDevice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMobileDeviceApplicationDTO {
    private List<MobileDeviceDTO> mobileDevice;
}
