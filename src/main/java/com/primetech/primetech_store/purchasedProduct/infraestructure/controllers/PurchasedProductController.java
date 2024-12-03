package com.primetech.primetech_store.purchasedProduct.infraestructure.controllers;

import com.primetech.primetech_store.purchasedProduct.application.AddPurchasedProductApplication;
import com.primetech.primetech_store.purchasedProduct.application.DTO.AddPurchasedProductRequestDTO;
import com.primetech.primetech_store.purchasedProduct.application.DTO.AddPurchasedProductResponseDTO;
import com.primetech.primetech_store.purchasedProduct.application.DTO.GetPurchasedProductsResponseDTO;
import com.primetech.primetech_store.purchasedProduct.application.DTO.PurchasedProductDetailsDTO;
import com.primetech.primetech_store.purchasedProduct.application.GetPurchasedProductsApplication;
import com.primetech.primetech_store.purchasedProduct.domain.models.PurchasedProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/purchased-product")
@RequiredArgsConstructor
public class PurchasedProductController {
    private final AddPurchasedProductApplication addPurchasedProductApplication;
    private final GetPurchasedProductsApplication getPurchasedProductsApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddPurchasedProductResponseDTO> addPurchasedProduct(@PathVariable UUID productId, @RequestBody AddPurchasedProductRequestDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            PurchasedProduct purchasedProduct = addPurchasedProductApplication.AddPurchasedProduct(authentication.getName(), request.getPurchaseQuantity() ,productId);
            return ResponseEntity.ok(new AddPurchasedProductResponseDTO("Purchased product successfully added", new PurchasedProductDetailsDTO(purchasedProduct)));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddPurchasedProductResponseDTO("Please log in", null));
        }
    }

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
