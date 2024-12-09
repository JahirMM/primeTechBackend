package com.primetech.primetech_store.common.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPurchaseAndSellProductRequestDTO {
    private UUID productId;
    private int purchaseQuantity;
}
