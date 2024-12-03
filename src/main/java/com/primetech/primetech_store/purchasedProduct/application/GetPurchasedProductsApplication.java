package com.primetech.primetech_store.purchasedProduct.application;

import com.primetech.primetech_store.purchasedProduct.application.DTO.PurchasedProductDetailsDTO;
import com.primetech.primetech_store.purchasedProduct.domain.interfaces.PurchasedProductServiceInterface;
import com.primetech.primetech_store.purchasedProduct.domain.models.PurchasedProduct;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class GetPurchasedProductsApplication {
    private final PurchasedProductServiceInterface purchasedProductService;
    private final UserServiceInterface userService;

    public List<PurchasedProductDetailsDTO> getPurchasedProducts(String email) {
        User user = userService.findUserInformationByEmail(email);
        List<PurchasedProduct> purchasedProducts = purchasedProductService.findByUserId(user.getUserId());

        List<PurchasedProductDetailsDTO> purchasedProductDetails = purchasedProducts.stream()
                .map(PurchasedProductDetailsDTO::new)
                .toList();

        return purchasedProductDetails;
    }
}
