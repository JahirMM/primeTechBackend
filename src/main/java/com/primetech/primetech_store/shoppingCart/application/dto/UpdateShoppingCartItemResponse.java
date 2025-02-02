package com.primetech.primetech_store.shoppingCart.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateShoppingCartItemResponse {
    private String message;
    private ShoppingCartItemUpdaterDTO updatedItem;
}
