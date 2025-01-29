package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.*;
import com.primetech.primetech_store.product.application.DTO.PriceRangeDTO;
import com.primetech.primetech_store.product.application.DTO.product.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/prime-tech/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final AddProductApplication addProductApplication;
    private final GetProductApplication getProductApplication;
    private final GetProductsApplication getProductsApplication;
    private final GetMinimumAndMaximumPrice getMinimumAndMaximumPrice;
    private final UpdateProductApplication updateProductApplication;
    private final DeleteProductApplication deleteProductApplication;

    @PostMapping("")
    public ResponseEntity<AddProductResponseDTO> addProduct(@Valid @RequestBody ProductRequestDTO request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            ProductDTO productDTO = addProductApplication.addProduct(request, authentication.getName());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new AddProductResponseDTO("Product successfully added", productDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddProductResponseDTO("Please log in", null));
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductResponseDTO> getProduct(@PathVariable UUID productId){
        ProductDTO product = getProductApplication.getProductApplication(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetProductResponseDTO(product));
    }

    @GetMapping("")
    public ResponseEntity<GetProductsResponseDTO> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String sellerId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Boolean onSale,
            @PageableDefault(page = 0, size = 20) Pageable pageable) {

        name = (name == null) ? "" : name;
        brand = (brand == null) ? "" : brand;

        PriceRangeDTO priceRange = getMinimumAndMaximumPrice.getMinimumAndMaximumPrice();
        minPrice = (minPrice == null) ? priceRange.getMinPrice() : minPrice;
        maxPrice = (maxPrice == null) ? priceRange.getMaxPrice() : maxPrice;


        UUID sellerUUID = null;
        UUID categoryUUID = null;

        try {
            if (sellerId != null) {
                sellerUUID = UUID.fromString(sellerId);
            }
            if (categoryId != null) {
                categoryUUID = UUID.fromString(categoryId);
            }
        } catch (IllegalArgumentException e) {
            GetProductsResponseDTO emptyResponse = new GetProductsResponseDTO(Collections.emptyList(), new PagedModel.PageMetadata(0, 0, 0));
            return ResponseEntity.status(HttpStatus.OK).body(emptyResponse);
        }


        Page<ProductDetailsDTO> products = getProductsApplication.getProductsApplication(name, brand,
                categoryUUID, sellerUUID,
                minPrice, maxPrice, minRating, onSale, pageable);
        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(products.getSize(),
                products.getNumber(),
                products.getTotalElements());

        GetProductsResponseDTO response = new GetProductsResponseDTO(products.getContent(), pageMetadata);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<UpdateProductResponseDTO> updateProduct(@Valid @RequestBody ProductRequestDTO request, @PathVariable UUID productId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            ProductDTO productDTO = updateProductApplication.updateProductApplication(productId, authentication.getName(), request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UpdateProductResponseDTO("Product successfully updated", productDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UpdateProductResponseDTO("Please log in", null));
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable UUID productId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> response = new HashMap<>();

        if (authentication != null && authentication.isAuthenticated()) {
            deleteProductApplication.deleteProductApplication(productId, authentication.getName());
            response.put("message", "Product deleted correctly");
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(response);
        } else {
            response.put("message", "Please log iny");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }
}
