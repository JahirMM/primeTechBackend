package com.primetech.primetech_store.soldProduct.application.DTO;

import com.primetech.primetech_store.soldProduct.domain.models.SoldProduct;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class SoldProductDetailsDTO {
    private UUID soldId;
    private UUID productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private String productImg;
    private int purchaseQuantity;
    private LocalDateTime saleDate;

    public SoldProductDetailsDTO(SoldProduct soldProduct) {
        this.soldId = soldProduct.getSoldId();
        this.productId = soldProduct.getProductId();
        this.productName = soldProduct.getProductName();
        this.productDescription = soldProduct.getProductDescription();
        this.productPrice = soldProduct.getProductPrice();
        this.productImg = soldProduct.getProductImg();
        this.purchaseQuantity = soldProduct.getPurchaseQuantity();
        this.saleDate = soldProduct.getSaleDate();
    }
}
