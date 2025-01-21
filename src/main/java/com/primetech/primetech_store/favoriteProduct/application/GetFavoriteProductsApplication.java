package com.primetech.primetech_store.favoriteProduct.application;

import com.primetech.primetech_store.favoriteProduct.application.DTO.FavoriteProductDetailsDTO;
import com.primetech.primetech_store.favoriteProduct.domain.interfaces.FavoriteProductServiceInterface;
import com.primetech.primetech_store.favoriteProduct.domain.models.FavoriteProduct;
import com.primetech.primetech_store.offer.domain.interfaces.OfferServiceInterface;
import com.primetech.primetech_store.offer.domain.models.Offer;
import com.primetech.primetech_store.product.application.DTO.product.ProductDetailsProjectionDTO;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.ProductImage;
import com.primetech.primetech_store.review.domain.interfaces.ReviewServicesInterface;
import com.primetech.primetech_store.review.domain.models.Review;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GetFavoriteProductsApplication {
    private final FavoriteProductServiceInterface favoriteProductService;
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;


    @Transactional
    public List<FavoriteProductDetailsDTO> getFavoriteProducts(String email) {
        User user = userService.findUserInformationByEmail(email);
        List<FavoriteProduct> favoriteProducts = favoriteProductService.getFavoriteProductByUserId(user.getUserId());

        return favoriteProducts.stream()
                .map(product -> {
                    ProductDetailsProjectionDTO productDetailsProjectionDTO = productService.findProductDetailsByProductId(product.getProduct().getProductId());
                    return new FavoriteProductDetailsDTO(
                            product.getProduct(),
                            product.getFavoriteProductId(),
                            productDetailsProjectionDTO.getImage(),
                            productDetailsProjectionDTO.getAverageRating(),
                            productDetailsProjectionDTO.isActiveOffer(),
                            productDetailsProjectionDTO.getDiscountPercentage()
                    );
                })
                .collect(Collectors.toList());
    }
}
