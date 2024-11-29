package com.primetech.primetech_store.ShoppingCart.infraestructure.controllers;

import com.primetech.primetech_store.ShoppingCart.application.AddProductToShoppingCartApplication;
import com.primetech.primetech_store.ShoppingCart.application.dto.AddProductToShoppingCartResponse;
import com.primetech.primetech_store.ShoppingCart.application.dto.ProductToShoppingCartAdderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final AddProductToShoppingCartApplication addProductToShoppingCartApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddProductToShoppingCartResponse> createShoppingCart(@PathVariable UUID productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            ProductToShoppingCartAdderDTO shoppingCart = addProductToShoppingCartApplication.addProductToShoppingCart(email, productId);

            return ResponseEntity.ok(new AddProductToShoppingCartResponse("Product added to the shopping cart", shoppingCart));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddProductToShoppingCartResponse("", null));
        }
    }
}
