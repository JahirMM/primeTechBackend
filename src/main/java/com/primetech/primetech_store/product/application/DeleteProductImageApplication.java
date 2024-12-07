package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.application.exception.FileStorageException;
import com.primetech.primetech_store.common.infraestructure.filesystem.FileStorageService;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.models.ProductImage;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor
public class DeleteProductImageApplication {
    private final ProductImageServiceInterface productImageService;
    private final FileStorageService fileStorageService;

    @Transactional
    public void DeleteProductImage(UUID productImageId) {
        ProductImage productImage = productImageService.findProductImageByProductImageId(productImageId);
        String imageUrl = productImage.getImgURL();
        productImageService.deleteProductImage(productImage.getProductImageId());

        try {
            fileStorageService.deleteFile(imageUrl);
        } catch (IOException e) {
            throw new FileStorageException("Could not delete previous image");
        }
    }
}
