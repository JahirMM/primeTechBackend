package com.primetech.primetech_store.purchasedProduct.infraestructure.controllers;

import com.primetech.primetech_store.purchasedProduct.application.AddPurchasedProductApplication;
import com.primetech.primetech_store.purchasedProduct.application.DTO.AddPurchasedProductRequestDTO;
import com.primetech.primetech_store.purchasedProduct.application.DTO.AddPurchasedProductResponseDTO;
import com.primetech.primetech_store.purchasedProduct.application.DTO.PurchasedProductDetails;
import com.primetech.primetech_store.purchasedProduct.domain.models.PurchasedProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/purchased-product")
@RequiredArgsConstructor
public class PurchasedProductController {
    private final AddPurchasedProductApplication addPurchasedProductApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddPurchasedProductResponseDTO> addPurchasedProduct(@PathVariable UUID productId, @RequestBody AddPurchasedProductRequestDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            PurchasedProduct purchasedProduct = addPurchasedProductApplication.AddPurchasedProduct(authentication.getName(), request.getPurchaseQuantity() ,productId);
            return ResponseEntity.ok(new AddPurchasedProductResponseDTO("Purchased product successfully added", new PurchasedProductDetails(purchasedProduct)));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddPurchasedProductResponseDTO("Please log in", null));
        }
    }
}
