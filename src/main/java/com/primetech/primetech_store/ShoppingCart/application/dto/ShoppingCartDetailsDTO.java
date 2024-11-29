package com.primetech.primetech_store.ShoppingCart.application.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ShoppingCartDetailsDTO {
    private UUID shoppingCartId;
    private LocalDateTime createdAt;
    private boolean completed;
    private List<ShoppingCartProductDetailsDTO> products;

    public ShoppingCartDetailsDTO(UUID shoppingCartId, LocalDateTime createdAt, boolean completed, List<ShoppingCartProductDetailsDTO> products) {
        this.shoppingCartId = shoppingCartId;
        this.createdAt = createdAt;
        this.completed = completed;
        this.products = products;
    }
}
