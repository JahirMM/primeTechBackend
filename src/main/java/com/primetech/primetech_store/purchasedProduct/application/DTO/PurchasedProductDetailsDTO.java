package com.primetech.primetech_store.purchasedProduct.application.DTO;

import com.primetech.primetech_store.purchasedProduct.domain.models.PurchasedProduct;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PurchasedProductDetailsDTO {
    private UUID purchaseId;
    private UUID productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private String productImg;
    private UUID sellerId;
    private String sellerName;
    private String sellerEmail;
    private int purchaseQuantity;

    public PurchasedProductDetailsDTO(PurchasedProduct purchasedProduct) {
        this.purchaseId = purchasedProduct.getPurchaseId();
        this.productId = purchasedProduct.getProductId();
        this.productName = purchasedProduct.getProductName();
        this.productDescription = purchasedProduct.getProductDescription();
        this.productPrice = purchasedProduct.getProductPrice();
        this.productImg = purchasedProduct.getProductImg();
        this.sellerId = purchasedProduct.getSellerId();
        this.sellerName = purchasedProduct.getSellerName();
        this.sellerEmail = purchasedProduct.getSellerEmail();
        this.purchaseQuantity = purchasedProduct.getPurchaseQuantity();
    }
}