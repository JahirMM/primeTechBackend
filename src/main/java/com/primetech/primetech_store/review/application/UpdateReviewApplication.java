package com.primetech.primetech_store.review.application;

import com.primetech.primetech_store.common.application.exception.InvalidRatingException;
import com.primetech.primetech_store.review.application.DTO.ReviewDTO;
import com.primetech.primetech_store.review.domain.interfaces.ReviewServicesInterface;
import com.primetech.primetech_store.review.domain.models.Review;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class UpdateReviewApplication {
    private final UserServiceInterface userService;
    private final ReviewServicesInterface reviewServices;

    @Transactional
    public ReviewDTO updateReview(String email, UUID reviewId, Double rating, String comment) {
        if (rating > 5 || rating < 0) {
            throw new InvalidRatingException("The rating provided is invalid.");
        }

        User user = userService.findUserInformationByEmail(email);
        Review review = reviewServices.findByUserIdAndReviewId(user.getUserId(), reviewId);
        review.setRating(rating);
        review.setComment(comment);

        Review saveReview = reviewServices.saveReview(review);

        return new ReviewDTO(saveReview);
    }
}
