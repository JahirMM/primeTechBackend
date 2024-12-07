package com.primetech.primetech_store.soldProduct.application.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSoldProductsResponseDTO {
    private String message;
    private List<SoldProductDetailsDTO> soldProduct;
}
