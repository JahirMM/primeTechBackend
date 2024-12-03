package com.primetech.primetech_store.purchasedProduct.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPurchasedProductsResponseDTO {
    private String message;
    private List<PurchasedProductDetailsDTO> purchasedProducts;
}
