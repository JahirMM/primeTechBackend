package com.primetech.primetech_store.product.application.DTO.laptop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLaptopResponseDTO {
    private String message;
    private LaptopDTO laptop;
}
