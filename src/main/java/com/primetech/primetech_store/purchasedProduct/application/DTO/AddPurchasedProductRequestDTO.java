package com.primetech.primetech_store.purchasedProduct.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPurchasedProductRequestDTO {
    private int purchaseQuantity;
}
