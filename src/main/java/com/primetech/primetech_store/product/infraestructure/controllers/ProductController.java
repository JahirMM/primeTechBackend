package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.AddProductApplication;
import com.primetech.primetech_store.product.application.DTO.*;
import com.primetech.primetech_store.product.application.GetProductApplication;
import com.primetech.primetech_store.product.application.GetProductsApplication;
import jakarta.validation.Valid;
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
@RequestMapping("/prime-tech/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final AddProductApplication addProductApplication;
    private final GetProductApplication getProductApplication;
    private final GetProductsApplication getProductsApplication;

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

    @GetMapping("")
    public ResponseEntity<Map<String, List<GetProductsResponseDTO>>> getProducts(){
        Map<String, List<GetProductsResponseDTO>> response = new HashMap<>();
        List<GetProductsResponseDTO> products = getProductsApplication.getProductsApplication();

        response.put("Products", products);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
