package com.primetech.primetech_store.product.application.DTO.laptop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLaptopResponsiveDTO {
    private List<LaptopDTO> laptop;
}
