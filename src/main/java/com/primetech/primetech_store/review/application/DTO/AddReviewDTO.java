package com.primetech.primetech_store.review.application.DTO;

import com.primetech.primetech_store.review.domain.models.Review;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AddReviewDTO {
    private UUID reviewId;
    private Double rating;
    private String comment;
    private LocalDateTime createdAt;

    public AddReviewDTO(Review review) {
        this.reviewId = review.getReviewId();
        this.rating = review.getRating();
        this.comment = review.getComment();
        this.createdAt = review.getCreatedAt();
    }
}
