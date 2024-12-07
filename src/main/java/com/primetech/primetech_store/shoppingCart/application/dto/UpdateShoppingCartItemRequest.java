package com.primetech.primetech_store.shoppingCart.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateShoppingCartItemRequest {
    private UUID shoppingCartId;
    private int quantity;
}
