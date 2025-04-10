package com.primetech.primetech_store.review.infrastructure.services;

import com.primetech.primetech_store.common.application.exception.ReviewNotFoundException;
import com.primetech.primetech_store.review.application.DTO.ReviewSummaryDTO;
import com.primetech.primetech_store.review.domain.interfaces.ReviewServicesInterface;
import com.primetech.primetech_store.review.domain.models.Review;
import com.primetech.primetech_store.review.infrastructure.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServices implements ReviewServicesInterface {
    private final ReviewRepository reviewRepository;

    @Override
    public Double findAverageRatingByProductId(UUID productId) {
        return reviewRepository.findAverageRatingByProductId(productId);
    }

    @Override
    public boolean findByProductIdAndUserId(UUID productId, UUID userId) {
        return reviewRepository.findByProductProductIdAndUserUserId(productId, userId).isPresent();
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review findByUserIdAndReviewId(UUID userId, UUID reviewId) {
        return reviewRepository.findByUserUserIdAndReviewId(userId, reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found for the given product and user."));
    }

    @Override
    public List<Review> findByProductId(UUID productId) {
        return reviewRepository.findByProductProductId(productId);
    }

    @Override
    public void deleteByReviewId(Review review) {
        reviewRepository.delete(review);
    }

    @Override
    public List<ReviewSummaryDTO> findReviewsSummaryByProductId(UUID productId) {
        return reviewRepository.findReviewsSummaryByProductProductId(productId);
    }
}
