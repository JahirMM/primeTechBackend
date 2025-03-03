package com.primetech.primetech_store.common.infrastructure.controllers;

import com.primetech.primetech_store.common.application.DTO.AddPurchaseAndSellProductRequestDTO;
import com.primetech.primetech_store.common.application.DTO.AddPurchaseAndSellProductResponseDTO;
import com.primetech.primetech_store.common.application.AddPurchaseAndSellProductApplication;
import com.primetech.primetech_store.purchasedProduct.application.DTO.PurchasedProductDetailsDTO;
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
public class PurchaseAndSellProductController {
    private final AddPurchaseAndSellProductApplication addPurchaseAndSellProductApplication;

    @PostMapping("")
    public ResponseEntity<AddPurchaseAndSellProductResponseDTO> addPurchaseAndSellProduct(@RequestBody List<AddPurchaseAndSellProductRequestDTO> request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            List<PurchasedProductDetailsDTO> purchasedProduct = addPurchaseAndSellProductApplication.addPurchaseAndSellProduct(authentication.getName(), request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new AddPurchaseAndSellProductResponseDTO("Purchased product successfully added", purchasedProduct));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddPurchaseAndSellProductResponseDTO("Please log in", null));
        }
    }
}
