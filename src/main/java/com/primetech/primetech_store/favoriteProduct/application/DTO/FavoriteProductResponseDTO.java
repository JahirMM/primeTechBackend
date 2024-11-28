package com.primetech.primetech_store.favoriteProduct.application.DTO;

import com.primetech.primetech_store.product.application.DTO.product.ProductDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteProductResponseDTO {
    private String message;
    private ProductDetailsDTO product;
}
