package com.primetech.primetech_store.review.domain.interfaces;

import com.primetech.primetech_store.review.domain.models.Review;

import java.util.UUID;

public interface ReviewServicesInterface {
    Double findAverageRatingByProductId(UUID productId);
    boolean findByProductIdAndUserId(UUID productId, UUID userId);
    Review saveReview(Review review);
}
