package com.primetech.primetech_store.common.application.DTO;


import com.primetech.primetech_store.purchasedProduct.application.DTO.PurchasedProductDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPurchaseAndSellProductResponseDTO {
    private String message;
    private List<PurchasedProductDetailsDTO> purchasedProducts;
}
