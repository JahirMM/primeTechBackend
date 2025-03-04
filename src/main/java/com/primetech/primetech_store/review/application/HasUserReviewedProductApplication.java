package com.primetech.primetech_store.review.application;

import com.primetech.primetech_store.common.application.exception.ProductNotPurchasedException;
import com.primetech.primetech_store.purchasedProduct.domain.interfaces.PurchasedProductServiceInterface;
import com.primetech.primetech_store.review.domain.interfaces.ReviewServicesInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class HasUserReviewedProductApplication {
    private final UserServiceInterface userService;
    private final ReviewServicesInterface reviewServices;
    private final PurchasedProductServiceInterface purchasedProductService;

    @Transactional
    public boolean hasUserReviewedProduct(String email, UUID productId) {
        User user = userService.findUserInformationByEmail(email);

        boolean hasPurchased = purchasedProductService.existsByProductIdAndUserId(productId, user.getUserId());
        if (!hasPurchased) {
            throw new ProductNotPurchasedException("You cannot review a product you have not purchased.");
        }

        return reviewServices.findByProductIdAndUserId(productId, user.getUserId());
    }
}
