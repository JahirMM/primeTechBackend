package com.primetech.primetech_store.soldProduct.application;

import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.soldProduct.domain.interfaces.SoldProductServiceInterface;
import com.primetech.primetech_store.soldProduct.domain.models.SoldProduct;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class AddSoldProductApplication {
    private final SoldProductServiceInterface soldProductService;

    @Transactional
    public void addSoldProduct(Product product, String imgUrl, int quantity) {
        SoldProduct soldProduct = new SoldProduct(product, imgUrl, quantity);
        soldProductService.saveSoldProduct(soldProduct);
    }
}
