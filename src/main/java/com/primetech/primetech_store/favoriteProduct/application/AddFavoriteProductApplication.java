package com.primetech.primetech_store.favoriteProduct.application;

import com.primetech.primetech_store.common.application.exception.ProductAlreadyFavoritedException;
import com.primetech.primetech_store.favoriteProduct.application.DTO.FavoriteProductDetailsDTO;
import com.primetech.primetech_store.favoriteProduct.domain.interfaces.FavoriteProductServiceInterface;
import com.primetech.primetech_store.favoriteProduct.domain.models.FavoriteProduct;
import com.primetech.primetech_store.product.application.DTO.product.ProductDetailsProjectionDTO;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.product.domain.models.ProductImage;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class AddFavoriteProductApplication {
    private final FavoriteProductServiceInterface favoriteProductService;
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;

    @Transactional
    public FavoriteProductDetailsDTO AddFavoriteProduct(String email, UUID productId) {
        User user = getUser(email);
        Product product = productService.findProductByProductId(productId);

        if (favoriteProductService.existsByProductIdAndUserId(product.getProductId(), user.getUserId())) {
            throw new ProductAlreadyFavoritedException("The product is already added as a favorite");
        }

        FavoriteProduct favoriteProduct = new FavoriteProduct(product, user);

        favoriteProductService.addFavoriteProduct(favoriteProduct);

        ProductDetailsProjectionDTO productDetailsProjectionDTO = productService.findProductDetailsByProductId(favoriteProduct.getProduct().getProductId());


        return new FavoriteProductDetailsDTO(productDetailsProjectionDTO.getProduct(), favoriteProduct.getFavoriteProductId(),
                productDetailsProjectionDTO.getImage(), productDetailsProjectionDTO.getAverageRating()
                , productDetailsProjectionDTO.isActiveOffer(), productDetailsProjectionDTO.getDiscountPercentage()
        );
    }


    private User getUser(String email) {
        return userService.findUserInformationByEmail(email);
    }
}