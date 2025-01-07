package com.primetech.primetech_store.review.infraestructure.controllers;

import com.primetech.primetech_store.review.application.AddReviewApplication;
import com.primetech.primetech_store.review.application.DTO.*;
import com.primetech.primetech_store.review.application.UpdateReviewApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final AddReviewApplication addReviewApplication;
    private final UpdateReviewApplication updateReviewApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddReviewResponseDTO> addReview(@Valid @RequestBody ReviewRequestDTO request, @PathVariable UUID productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            AddReviewDTO reviewDTO = addReviewApplication.addReview(authentication.getName(), productId, request.getRating(), request.getComment());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new AddReviewResponseDTO("Review successfully added", reviewDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddReviewResponseDTO("Please log in", null));
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<UpdateReviewResponseDTO> updateReview(@Valid @RequestBody ReviewRequestDTO request, @PathVariable UUID reviewId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            ReviewDTO reviewDTO = updateReviewApplication.updateReview(authentication.getName(), reviewId, request.getRating(), request.getComment());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UpdateReviewResponseDTO("Review successfully updated", reviewDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UpdateReviewResponseDTO("Please log in", null));
        }
    }
}
