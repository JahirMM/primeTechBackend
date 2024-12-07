package com.primetech.primetech_store.soldProduct.infraestructure.controllers;

import com.primetech.primetech_store.purchasedProduct.application.DTO.GetPurchasedProductsResponseDTO;
import com.primetech.primetech_store.purchasedProduct.application.DTO.PurchasedProductDetailsDTO;
import com.primetech.primetech_store.soldProduct.application.DTO.GetSoldProductsResponseDTO;
import com.primetech.primetech_store.soldProduct.application.DTO.SoldProductDetailsDTO;
import com.primetech.primetech_store.soldProduct.application.GetSoldProductsApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prime-tech/api/v1/sales")
@RequiredArgsConstructor
public class SoldProductController {
    private final GetSoldProductsApplication getPurchasedProducts;

    @GetMapping("")
    public ResponseEntity<GetSoldProductsResponseDTO> getSoldProducts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            List<SoldProductDetailsDTO> soldProductDetails = getPurchasedProducts.getSoldProducts(authentication.getName());
            return ResponseEntity.ok(new GetSoldProductsResponseDTO("Products found", soldProductDetails));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new GetSoldProductsResponseDTO("Please log in", null));
        }
    }
}
