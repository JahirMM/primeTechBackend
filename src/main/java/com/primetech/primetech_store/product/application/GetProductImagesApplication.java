package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.product.application.DTO.productImage.ProductImageDTO;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.models.ProductImage;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GetProductImagesApplication {

    private final ProductImageServiceInterface productImageService;

    public List<ProductImageDTO> GetProductImages(UUID productId) {
        List<ProductImage> productImages = productImageService.findProductImage(productId);
        return productImages.stream()
                .map(image -> new ProductImageDTO(image.getProductImageId(), image.getImgURL(), image.isMain()))
                .collect(Collectors.toList());
    }
}
