package com.primetech.primetech_store.product.infrastructure.controllers;

import com.primetech.primetech_store.product.application.DTO.product.GetUserProductsResponseDTO;
import com.primetech.primetech_store.product.application.DTO.product.UserProductDTO;
import com.primetech.primetech_store.product.application.GetUserProductsApplication;
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
@RequestMapping("/prime-tech/api/v1/user-products")
@RequiredArgsConstructor
public class UserProductsController {
    private final GetUserProductsApplication getUserProducts;

    @GetMapping("")
    public ResponseEntity<GetUserProductsResponseDTO> getUserProducts(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            List<UserProductDTO> products = getUserProducts.getUserProducts(authentication.getName());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new GetUserProductsResponseDTO("Products found", products));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new GetUserProductsResponseDTO("Please log in", null));
        }
    }
}
