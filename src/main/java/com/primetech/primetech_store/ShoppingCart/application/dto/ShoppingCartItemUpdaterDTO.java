package com.primetech.primetech_store.ShoppingCart.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItemUpdaterDTO {
    private UUID shoppingCartId;
    private UUID productId;
    private int quantity;
}