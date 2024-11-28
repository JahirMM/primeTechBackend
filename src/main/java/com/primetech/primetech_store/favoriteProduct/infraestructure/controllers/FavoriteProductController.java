package com.primetech.primetech_store.favoriteProduct.infraestructure.controllers;

import com.primetech.primetech_store.favoriteProduct.application.AddFavoriteProductApplication;
import com.primetech.primetech_store.favoriteProduct.application.DTO.FavoriteProductResponseDTO;
import com.primetech.primetech_store.favoriteProduct.domain.models.FavoriteProduct;
import com.primetech.primetech_store.product.application.DTO.product.ProductDetailsDTO;
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
@RequestMapping("/prime-tech/api/v1/favorite-products")
@RequiredArgsConstructor
public class FavoriteProductController {
    private final AddFavoriteProductApplication addFavoriteProductApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<FavoriteProductResponseDTO> addFavoriteProduct(@PathVariable UUID productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            FavoriteProduct favoriteProduct = addFavoriteProductApplication.AddFavoriteProduct(email, productId);
            ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO(favoriteProduct.getProduct());
            return ResponseEntity.ok(new FavoriteProductResponseDTO("Favorite product added", productDetailsDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new FavoriteProductResponseDTO("Please log in", null));
        }
    }
}
