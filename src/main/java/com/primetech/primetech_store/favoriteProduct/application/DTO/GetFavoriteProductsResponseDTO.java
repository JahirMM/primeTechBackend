package com.primetech.primetech_store.favoriteProduct.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFavoriteProductsResponseDTO {
    private String message;
    private List<FavoriteProductDetailsDTO> favoriteProducts;
}
