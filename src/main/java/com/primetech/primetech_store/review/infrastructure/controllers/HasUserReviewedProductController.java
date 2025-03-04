package com.primetech.primetech_store.review.infrastructure.controllers;

import com.primetech.primetech_store.review.application.HasUserReviewedProductApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/has-review")
@RequiredArgsConstructor
public class HasUserReviewedProductController {
    private final HasUserReviewedProductApplication hasUserReviewedProductApplication;

    @GetMapping("/{productId}")
    public ResponseEntity<Map<String, Boolean>> hasUserReview(@PathVariable UUID productId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Boolean hasReview = hasUserReviewedProductApplication.hasUserReviewedProduct(authentication.getName(), productId);
        Map<String, Boolean> responseMap = new HashMap<>();
        responseMap.put("hasReview", hasReview);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseMap);
    }
}
