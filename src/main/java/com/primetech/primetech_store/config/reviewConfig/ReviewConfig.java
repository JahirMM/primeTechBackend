package com.primetech.primetech_store.config.reviewConfig;

import com.primetech.primetech_store.review.application.GetAverageRatingByProductIdApplication;
import com.primetech.primetech_store.review.domain.interfaces.ReviewServicesInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ReviewConfig {
    private final ReviewServicesInterface reviewServices;

    @Bean
    public GetAverageRatingByProductIdApplication getAverageRatingByProductIdApplication() {
        return new GetAverageRatingByProductIdApplication(reviewServices);
    }
}
