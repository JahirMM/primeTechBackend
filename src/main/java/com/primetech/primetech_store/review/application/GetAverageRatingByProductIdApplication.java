package com.primetech.primetech_store.review.application;

import com.primetech.primetech_store.review.domain.interfaces.ReviewServicesInterface;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class GetAverageRatingByProductIdApplication {
    private final ReviewServicesInterface reviewServices;

    @Transactional
    public Double getAverageRatingByProductId(UUID productId) {
        return reviewServices.findAverageRatingByProductId(productId);
    }
}
