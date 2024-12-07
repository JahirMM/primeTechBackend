package com.primetech.primetech_store.common.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPurchaseAndSellProductRequestDTO {
    private int purchaseQuantity;
}
