package com.primetech.primetech_store.favoriteProduct.infraestructure.controllers;

import com.primetech.primetech_store.favoriteProduct.application.AddFavoriteProductApplication;
import com.primetech.primetech_store.favoriteProduct.application.DTO.FavoriteProductDetailsDTO;
import com.primetech.primetech_store.favoriteProduct.application.DTO.FavoriteProductResponseDTO;
import com.primetech.primetech_store.favoriteProduct.application.DTO.GetFavoriteProductsResponseDTO;
import com.primetech.primetech_store.favoriteProduct.application.DeleteFavoriteProductApplication;
import com.primetech.primetech_store.favoriteProduct.application.GetFavoriteProductsApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/favorite-products")
@RequiredArgsConstructor
public class FavoriteProductController {
    private final AddFavoriteProductApplication addFavoriteProductApplication;
    private final GetFavoriteProductsApplication getFavoriteProductsApplication;
    private final DeleteFavoriteProductApplication deleteFavoriteProductApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<FavoriteProductResponseDTO> addFavoriteProduct(@PathVariable UUID productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            FavoriteProductDetailsDTO favoriteProduct = addFavoriteProductApplication.AddFavoriteProduct(email, productId);
            return ResponseEntity.ok(new FavoriteProductResponseDTO("Favorite product added",favoriteProduct));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new FavoriteProductResponseDTO("Please log in", null));
        }
    }

    @GetMapping("")
    public ResponseEntity<GetFavoriteProductsResponseDTO> getFavoriteProducts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            List<FavoriteProductDetailsDTO> favoriteProductDetailsDTOS = getFavoriteProductsApplication.getFavoriteProducts(email);
            return ResponseEntity.ok(new GetFavoriteProductsResponseDTO("Favorite products found", favoriteProductDetailsDTOS));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new GetFavoriteProductsResponseDTO("Please log in", null));
        }
    }

    @DeleteMapping("/{favoriteProductId}")
    public ResponseEntity<Map<String, String>> deleteFavoriteProducts(@PathVariable UUID favoriteProductId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> response = new HashMap<>();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            deleteFavoriteProductApplication.deleteFavoriteProduct(email, favoriteProductId);
            response.put("message", "Favorite product successfully deleted");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Please log in");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }
}
