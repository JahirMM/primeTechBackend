package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.DTO.productImage.UploadImageRequestDTO;
import com.primetech.primetech_store.product.application.UploadProductImageApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/product-image")
@RequiredArgsConstructor
public class ProductImageController {
    private final UploadProductImageApplication uploadProductImageApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<Map<String, String>> uploadImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam("isMain") Boolean isMain,
            @PathVariable("productId") UUID productId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            UploadImageRequestDTO request = new UploadImageRequestDTO();
            request.setMain(isMain);

            uploadProductImageApplication.uploadProductImage(file, email, productId, request);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Image successfully uploaded");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Please log in");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
