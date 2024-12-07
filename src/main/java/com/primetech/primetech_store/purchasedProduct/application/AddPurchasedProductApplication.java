package com.primetech.primetech_store.purchasedProduct.application;

import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.purchasedProduct.domain.interfaces.PurchasedProductServiceInterface;
import com.primetech.primetech_store.purchasedProduct.domain.models.PurchasedProduct;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class AddPurchasedProductApplication {
    private final PurchasedProductServiceInterface purchasedProductService;

    @Transactional
    public PurchasedProduct addPurchasedProduct(User user, Product product, String imgUrl, int quantity) {
        PurchasedProduct purchasedProduct = new PurchasedProduct(user, product, imgUrl, quantity);

        return purchasedProductService.savePurchasedProduct(purchasedProduct);
    }
}
