package com.primetech.primetech_store.review.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateReviewResponseDTO {
    private String message;
    private ReviewDTO review;
}
