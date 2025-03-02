package com.primetech.primetech_store.recentProducts.application;

import com.primetech.primetech_store.offer.domain.interfaces.OfferServiceInterface;
import com.primetech.primetech_store.product.application.DTO.product.ProductDetailsProjectionDTO;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.recentProducts.application.DTO.RecentProductDTO;
import com.primetech.primetech_store.recentProducts.domain.interfaces.RecentProductServiceInterface;
import com.primetech.primetech_store.recentProducts.domain.models.RecentProduct;
import com.primetech.primetech_store.review.domain.interfaces.ReviewServicesInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GetRecentProductApplication {
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;
    private final RecentProductServiceInterface recentProductService;

    @Transactional
    public List<RecentProductDTO> getRecentProduct(String email) {
        User user = userService.findUserInformationByEmail(email);
        List<RecentProduct> recentProducts = recentProductService.getRecentProducts(user.getUserId());

        return recentProducts.stream().map(recentProduct -> {
            Product product = productService.findProductByProductId(recentProduct.getProduct().getProductId());


            ProductDetailsProjectionDTO productDetailsProjectionDTO = productService.findProductDetailsByProductId(product.getProductId());
            return new RecentProductDTO(
                    productDetailsProjectionDTO.getProduct().getProductId(),
                    productDetailsProjectionDTO.getProduct().getName(),
                    productDetailsProjectionDTO.getProduct().getBrand(),
                    productDetailsProjectionDTO.getProduct().getPrice(),
                    productDetailsProjectionDTO.getAverageRating(),
                    productDetailsProjectionDTO.isActiveOffer(),
                    productDetailsProjectionDTO.getDiscountPercentage(),
                    productDetailsProjectionDTO.getImage()
            );
        }).collect(Collectors.toList());
    }
}
