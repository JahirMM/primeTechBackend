package com.primetech.primetech_store.review.domain.interfaces;

import com.primetech.primetech_store.review.application.DTO.ReviewSummaryDTO;
import com.primetech.primetech_store.review.domain.models.Review;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReviewServicesInterface {
    Double findAverageRatingByProductId(UUID productId);
    boolean findByProductIdAndUserId(UUID productId, UUID userId);
    Review saveReview(Review review);
    Review findByUserIdAndReviewId(UUID userId, UUID reviewId);
    List<Review> findByProductId(UUID productId);
    void deleteByReviewId(Review review);
    List<ReviewSummaryDTO> findReviewsSummaryByProductId(UUID productId);
}
