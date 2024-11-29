package com.primetech.primetech_store.favoriteProduct.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteProductResponseDTO {
    private String message;
    private FavoriteProductDetailsDTO favoriteProduct;
}
