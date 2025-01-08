package com.primetech.primetech_store.review.application;

import com.primetech.primetech_store.review.application.DTO.ReviewDetailsDTO;
import com.primetech.primetech_store.review.application.DTO.ReviewSummaryDTO;
import com.primetech.primetech_store.review.domain.interfaces.ReviewServicesInterface;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class GetReviewsApplication {
    private final ReviewServicesInterface reviewServices;

    @Transactional
    public List<ReviewDetailsDTO> getReviews(UUID productId) {
        List<ReviewSummaryDTO> reviewSummaryList = reviewServices.findReviewsSummaryByProductId(productId);

        return reviewSummaryList.stream().map(review ->
                new ReviewDetailsDTO(review.getUser(), review.getReviewId(),review.getRating(), review.getComment(), review.getCreatedAt())
        ).toList();
    }
}
