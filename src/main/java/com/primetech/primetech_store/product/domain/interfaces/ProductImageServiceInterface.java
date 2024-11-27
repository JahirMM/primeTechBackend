package com.primetech.primetech_store.product.domain.interfaces;

import com.primetech.primetech_store.product.domain.models.ProductImage;

import java.util.List;
import java.util.UUID;

public interface ProductImageServiceInterface {
    void uploadProductImage(UUID productId, String url, boolean isMain);
    List<ProductImage> findProductImage(UUID productId);
    void deleteProductImage(UUID productImageId);
    int countProductImageByProductId(UUID productId);
    boolean existsProductByProductIdAndMainTrue(UUID productId);
    ProductImage findProductImageByProductImageId(UUID productImageId);
}
