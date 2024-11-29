package com.primetech.primetech_store.ShoppingCart.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetShoppingCartResponseDTO {
    private String message;
    private ShoppingCartDetailsDTO shoppingCart;
}
