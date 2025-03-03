package com.primetech.primetech_store.purchasedProduct.infrastructure.controllers;

import com.primetech.primetech_store.purchasedProduct.application.DTO.GetPurchasedProductsResponseDTO;
import com.primetech.primetech_store.purchasedProduct.application.DTO.PurchasedProductDetailsDTO;
import com.primetech.primetech_store.purchasedProduct.application.GetPurchasedProductsApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prime-tech/api/v1/purchases")
@RequiredArgsConstructor
public class PurchasedProductController {
    private final GetPurchasedProductsApplication getPurchasedProductsApplication;

    @GetMapping("")
    public ResponseEntity<GetPurchasedProductsResponseDTO> getPurchasedProducts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            List<PurchasedProductDetailsDTO> purchasedProducts = getPurchasedProductsApplication.getPurchasedProducts(authentication.getName());
            return ResponseEntity.ok(new GetPurchasedProductsResponseDTO("Products found", purchasedProducts));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new GetPurchasedProductsResponseDTO("Please log in", null));
        }
    }
}
