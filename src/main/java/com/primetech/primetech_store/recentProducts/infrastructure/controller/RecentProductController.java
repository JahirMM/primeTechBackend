package com.primetech.primetech_store.recentProducts.infrastructure.controller;

import com.primetech.primetech_store.recentProducts.application.AddRecentProductApplication;
import com.primetech.primetech_store.recentProducts.application.DTO.RecentProductDTO;
import com.primetech.primetech_store.recentProducts.application.GetRecentProductApplication;
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
@RequestMapping("/prime-tech/api/v1/recent-product")
@RequiredArgsConstructor
public class RecentProductController {
    private final GetRecentProductApplication getRecentProductApplication;
    private final AddRecentProductApplication addRecentProductApplication;

    @GetMapping
    public ResponseEntity<List<RecentProductDTO>> getRecentProducts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<RecentProductDTO> recentProducts = getRecentProductApplication.getRecentProduct(authentication.getName());
        return ResponseEntity.ok(recentProducts);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Map<String, String>> addRecentProduct(@PathVariable UUID productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        addRecentProductApplication.addRecentProduct(authentication.getName(), productId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Producto reciente agregado correctamente.");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
