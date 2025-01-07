package com.primetech.primetech_store.review.application;

import com.primetech.primetech_store.common.application.exception.InvalidRatingException;
import com.primetech.primetech_store.common.application.exception.ProductNotPurchasedException;
import com.primetech.primetech_store.common.application.exception.ReviewAlreadyExistsException;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.purchasedProduct.domain.interfaces.PurchasedProductServiceInterface;
import com.primetech.primetech_store.review.application.DTO.AddReviewDTO;
import com.primetech.primetech_store.review.domain.interfaces.ReviewServicesInterface;
import com.primetech.primetech_store.review.domain.models.Review;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class AddReviewApplication {
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;
    private final ReviewServicesInterface reviewServices;
    private final PurchasedProductServiceInterface purchasedProductService;

    @Transactional
    public AddReviewDTO addReview(String email, UUID productId, Double rating, String comment) {
        if (rating > 5 || rating < 0) {
            throw new InvalidRatingException("The rating provided is invalid.");
        }

        User user = userService.findUserInformationByEmail(email);
        Product product = productService.findProductByProductId(productId);

        boolean hasPurchased = purchasedProductService.existsByProductIdAndUserId(productId, user.getUserId());
        if (!hasPurchased) {
            throw new ProductNotPurchasedException("You cannot review a product you have not purchased.");
        }

        boolean hasReview = reviewServices.findByProductIdAndUserId(productId, user.getUserId());
        if (hasReview) {
            throw new ReviewAlreadyExistsException("You have already submitted a review for this product.");
        }

        Review review = reviewServices.saveReview(new Review(product, user, rating, comment));
        return new AddReviewDTO(review);
    }
}
