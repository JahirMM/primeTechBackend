package com.primetech.primetech_store.shoppingCart.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductToShoppingCartAdderDTO {
    private UUID shoppingCartId;
    private LocalDateTime createdAt;
    private boolean isCompleted;
    private ShoppingCartProductDetailsDTO product;
}
