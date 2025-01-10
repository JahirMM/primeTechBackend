package com.primetech.primetech_store.Offer.application;

import com.primetech.primetech_store.Offer.application.DTO.OfferDTO;
import com.primetech.primetech_store.Offer.application.DTO.OfferRequestDTO;
import com.primetech.primetech_store.Offer.domain.interfaces.OfferServiceInterface;
import com.primetech.primetech_store.Offer.domain.models.Offer;
import com.primetech.primetech_store.common.application.exception.OfferAlreadyExistsException;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class AddOfferApplication {
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;
    private final OfferServiceInterface offerService;

    @Transactional
    public OfferDTO addOffer(OfferRequestDTO request, String email, UUID productId) {
        User seller = userService.findUserInformationByEmail(email);
        Product product = productService.findByProductIdAndSellerId(productId, seller.getUserId());

        if (offerService.existsByProduct(product)) {
            throw new OfferAlreadyExistsException("he product already has an offer. Please use the PUT API to update it");
        }

        Offer offer = new Offer(product, request.getDiscountPercentage(), request.getStartDate(), request.getEndDate(), true);

        return new OfferDTO(offerService.addOffer(offer));
    }
}
