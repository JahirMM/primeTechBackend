package com.primetech.primetech_store.product.application.DTO.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductResponseDTO {
    private String message;
    private ProductDTO product;
}
