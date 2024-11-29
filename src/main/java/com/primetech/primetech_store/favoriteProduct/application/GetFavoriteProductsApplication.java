package com.primetech.primetech_store.favoriteProduct.application;

import com.primetech.primetech_store.favoriteProduct.application.DTO.FavoriteProductDetailsDTO;
import com.primetech.primetech_store.favoriteProduct.domain.interfaces.FavoriteProductServiceInterface;
import com.primetech.primetech_store.favoriteProduct.domain.models.FavoriteProduct;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.models.ProductImage;
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
    private final ProductImageServiceInterface productImageService;

    @Transactional
    public List<FavoriteProductDetailsDTO> getFavoriteProducts(String email) {
        User user = userService.findUserInformationByEmail(email);
        List<FavoriteProduct> favoriteProducts = favoriteProductService.getFavoriteProductByUserId(user.getUserId());

        return favoriteProducts.stream()
                .map(product -> new FavoriteProductDetailsDTO(
                        product.getProduct(),
                        product.getFavoriteProductId(),
                        getImgUrl(product.getProduct().getProductId())))
                .collect(Collectors.toList());
    }

    public String getImgUrl(UUID productId) {
        ProductImage productImage = productImageService.findProductImagByProductIdAndMainTrue(productId);
        return (productImage != null) ? productImage.getImgURL() : null;
    }
}
