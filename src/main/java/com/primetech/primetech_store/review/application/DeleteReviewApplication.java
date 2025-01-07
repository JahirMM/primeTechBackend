package com.primetech.primetech_store.review.application;

import com.primetech.primetech_store.review.domain.interfaces.ReviewServicesInterface;
import com.primetech.primetech_store.review.domain.models.Review;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class DeleteReviewApplication {
    private final UserServiceInterface userService;
    private final ReviewServicesInterface reviewServices;

    @Transactional
    public void deleteReview(String email, UUID reviewId) {
        User user = userService.findUserInformationByEmail(email);
        Review review = reviewServices.findByUserIdAndReviewId(user.getUserId(), reviewId);

        reviewServices.deleteByReviewId(review);
    }
}
