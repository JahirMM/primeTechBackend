package com.primetech.primetech_store.soldProduct.application;

import com.primetech.primetech_store.soldProduct.application.DTO.SoldProductDetailsDTO;
import com.primetech.primetech_store.soldProduct.domain.interfaces.SoldProductServiceInterface;
import com.primetech.primetech_store.soldProduct.domain.models.SoldProduct;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetSoldProductsApplication {
    private final SoldProductServiceInterface soldProductService;
    private final UserServiceInterface userService;

    public List<SoldProductDetailsDTO> getSoldProducts(String email) {
        User user = userService.findUserInformationByEmail(email);
        List<SoldProduct> soldProducts = soldProductService.findByUserId(user.getUserId());

        List<SoldProductDetailsDTO> soldProductDetails = soldProducts.stream()
                .map(SoldProductDetailsDTO::new)
                .toList();

        return soldProductDetails;
    }
}
