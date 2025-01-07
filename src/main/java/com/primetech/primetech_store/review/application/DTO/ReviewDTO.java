package com.primetech.primetech_store.review.application.DTO;

import com.primetech.primetech_store.review.domain.models.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private UUID reviewId;
    private Double rating;
    private String comment;
    private LocalDateTime createdAt;

    public ReviewDTO(Review review) {
        this.reviewId = review.getReviewId();
        this.rating = review.getRating();
        this.comment = review.getComment();
        this.createdAt = review.getCreatedAt();
    }
}
