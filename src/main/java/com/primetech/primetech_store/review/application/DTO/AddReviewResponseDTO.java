package com.primetech.primetech_store.review.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddReviewResponseDTO {
    private final String message;
    private final AddReviewDTO review;
}
