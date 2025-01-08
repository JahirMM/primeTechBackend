package com.primetech.primetech_store.review.infraestructure.repositories;

import com.primetech.primetech_store.review.application.DTO.ReviewSummaryDTO;
import com.primetech.primetech_store.review.domain.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    @Query("SELECT COALESCE(AVG(r.rating), 0) FROM Review r WHERE r.product.productId = :productId")
    Double findAverageRatingByProductId(@Param("productId") UUID productId);
    Optional<Review> findByProductProductIdAndUserUserId(UUID productId, UUID userId);
    Optional<Review> findByUserUserIdAndReviewId(UUID userId, UUID reviewId);
    @Query("SELECT new com.primetech.primetech_store.review.application.DTO.ReviewSummaryDTO(r.user, r.reviewId, r.rating, r.comment, r.createdAt) " +
            "FROM Review r WHERE r.product.productId = :productId")
    List<ReviewSummaryDTO> findReviewsSummaryByProductProductId(@Param("productId") UUID productId);
}
