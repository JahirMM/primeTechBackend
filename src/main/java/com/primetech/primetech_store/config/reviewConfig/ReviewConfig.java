package com.primetech.primetech_store.config.reviewConfig;

import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.purchasedProduct.domain.interfaces.PurchasedProductServiceInterface;
import com.primetech.primetech_store.review.application.*;
import com.primetech.primetech_store.review.domain.interfaces.ReviewServicesInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ReviewConfig {
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;
    private final ReviewServicesInterface reviewServices;
    private final PurchasedProductServiceInterface purchasedProductService;

    @Bean
    public GetAverageRatingByProductIdApplication getAverageRatingByProductIdApplication() {
        return new GetAverageRatingByProductIdApplication(reviewServices);
    }

    @Bean
    public HasUserReviewedProductApplication hasUserReviewedProductApplication() {
        return new HasUserReviewedProductApplication(
                userService, reviewServices,
                purchasedProductService
        );
    }

    @Bean
    public AddReviewApplication addReviewApplication() {
        return new AddReviewApplication(
                userService, productService,
                reviewServices, purchasedProductService
        );
    }

    @Bean
    public UpdateReviewApplication updateReviewApplication() {
        return new UpdateReviewApplication(
                userService, reviewServices
        );
    }

    @Bean
    public DeleteReviewApplication delteDeleteReviewApplication() {
        return new DeleteReviewApplication(
                userService, reviewServices
        );
    }

    @Bean
    public GetReviewsApplication getReviewsApplication() {
        return new GetReviewsApplication(
                reviewServices
        );
    }
}
