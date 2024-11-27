package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.DTO.productImage.GetProductImagesResponseDTO;
import com.primetech.primetech_store.product.application.DTO.productImage.ProductImageDTO;
import com.primetech.primetech_store.product.application.DTO.productImage.UploadImageRequestDTO;
import com.primetech.primetech_store.product.application.DeleteProductImageApplication;
import com.primetech.primetech_store.product.application.GetProductImagesApplication;
import com.primetech.primetech_store.product.application.UploadProductImageApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/product-image")
@RequiredArgsConstructor
public class ProductImageController {
    private final UploadProductImageApplication uploadProductImageApplication;
    private final DeleteProductImageApplication deleteProductImageApplication;
    private final GetProductImagesApplication getProductImagesApplication;

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductImagesResponseDTO> getProductImages(@PathVariable("productId") UUID productId) {
        List<ProductImageDTO> productImages = getProductImagesApplication.GetProductImages(productId);
        return ResponseEntity.ok(new GetProductImagesResponseDTO(productImages));
    }

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

    @DeleteMapping("/{productImageId}")
    public ResponseEntity<Map<String, String>> deleteProductImage(@PathVariable("productImageId") UUID productImageId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            deleteProductImageApplication.DeleteProductImage(productImageId);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Image deleted correctly");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Please log in");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
