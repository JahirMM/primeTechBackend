package com.primetech.primetech_store.review.infraestructure.services;

import com.primetech.primetech_store.review.domain.interfaces.ReviewServicesInterface;
import com.primetech.primetech_store.review.infraestructure.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServices implements ReviewServicesInterface {
    private final ReviewRepository reviewRepository;

    @Override
    public Double findAverageRatingByProductId(UUID productId) {
        return reviewRepository.findAverageRatingByProductId(productId);
    }
}
