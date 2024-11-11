package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.AddProductApplication;
import com.primetech.primetech_store.product.application.DTO.AddProductRequestDTO;
import com.primetech.primetech_store.product.application.DTO.AddProductResponseDTO;
import com.primetech.primetech_store.product.application.DTO.GetProductResponseDTO;
import com.primetech.primetech_store.product.application.DTO.ProductDTO;
import com.primetech.primetech_store.product.application.GetProductApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final AddProductApplication addProductApplication;
    private final GetProductApplication getProductApplication;

    @PostMapping("")
    public ResponseEntity<AddProductResponseDTO> addProduct(@Valid @RequestBody AddProductRequestDTO request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            ProductDTO productDTO = addProductApplication.addProduct(request, authentication.getName());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new AddProductResponseDTO("Product successfully added", productDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddProductResponseDTO("Please log in", null));
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductResponseDTO> getProduct(@PathVariable UUID productId){
        ProductDTO productDTO = getProductApplication.getProductApplication(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetProductResponseDTO(productDTO));
    }
}
