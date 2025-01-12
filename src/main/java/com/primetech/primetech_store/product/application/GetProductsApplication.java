package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.application.exception.InvalidRatingException;
import com.primetech.primetech_store.offer.domain.interfaces.OfferServiceInterface;
import com.primetech.primetech_store.offer.domain.models.Offer;
import com.primetech.primetech_store.product.application.DTO.product.ProductDetailsDTO;
import com.primetech.primetech_store.product.application.DTO.product.ProductDetailsProjectionDTO;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.product.domain.models.ProductImage;
import com.primetech.primetech_store.review.application.GetAverageRatingByProductIdApplication;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
public class GetProductsApplication {
    private final ProductServiceInterface productService;

    @Transactional
    public Page<ProductDetailsDTO> getProductsApplication(String name, String brand, UUID categoryId,
                                                          UUID sellerId, BigDecimal minPrice, BigDecimal maxPrice,
                                                          Double rating, Boolean onSale, Pageable pageable) {

        if (rating != null) {
            if (rating > 5 || rating < 0) {
                throw new InvalidRatingException("The rating provided is invalid.");
            }
        }

        Page<Product> products = productService.findAllProducts(name, brand, categoryId, sellerId, minPrice, maxPrice, rating, onSale, pageable);
        return products.map(product -> {
            ProductDetailsProjectionDTO productDetailsProjectionDTO = productService.findProductDetailsByProductId(product.getProductId());
            return new ProductDetailsDTO(
                    productDetailsProjectionDTO.getProduct(), productDetailsProjectionDTO.getImage(), productDetailsProjectionDTO.getDeviceType(),
                    productDetailsProjectionDTO.getAverageRating(), productDetailsProjectionDTO.getDiscountPercentage(), productDetailsProjectionDTO.isActiveOffer()
            );
        });
    }
}
