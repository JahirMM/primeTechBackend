package com.primetech.primetech_store.ShoppingCart.infraestructure.controllers;

import com.primetech.primetech_store.ShoppingCart.application.AddProductToShoppingCartApplication;
import com.primetech.primetech_store.ShoppingCart.application.GetShoppingCartApplication;
import com.primetech.primetech_store.ShoppingCart.application.UpdateShoppingCartItemApplication;
import com.primetech.primetech_store.ShoppingCart.application.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final AddProductToShoppingCartApplication addProductToShoppingCartApplication;
    private final GetShoppingCartApplication getShoppingCartApplication;
    private final UpdateShoppingCartItemApplication updateShoppingCartItemApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddProductToShoppingCartResponse> createShoppingCart(@PathVariable UUID productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            ProductToShoppingCartAdderDTO shoppingCart = addProductToShoppingCartApplication.addProductToShoppingCart(email, productId);

            return ResponseEntity.ok(new AddProductToShoppingCartResponse("Product added to the shopping cart", shoppingCart));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddProductToShoppingCartResponse("Please log in", null));
        }
    }

    @GetMapping("")
    public ResponseEntity<GetShoppingCartResponseDTO> getShoppingCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            ShoppingCartDetailsDTO shoppingCart = getShoppingCartApplication.getShoppingCart(email);
            return ResponseEntity.ok(new GetShoppingCartResponseDTO("Shopping cart found", shoppingCart));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new GetShoppingCartResponseDTO("Please log in", null));
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<UpdateShoppingCartItemResponse> updateShoppingCartItem(
            @PathVariable UUID productId,
            @RequestBody UpdateShoppingCartItemRequest request
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            ShoppingCartItemUpdaterDTO updatedItem = updateShoppingCartItemApplication.updateShoppingCartItem(
                    request.getShoppingCartId(),
                    productId,
                    request.getQuantity()
            );

            return ResponseEntity.ok(new UpdateShoppingCartItemResponse("Product quantity updated", updatedItem));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UpdateShoppingCartItemResponse("Please log in", null));
        }
    }
}
