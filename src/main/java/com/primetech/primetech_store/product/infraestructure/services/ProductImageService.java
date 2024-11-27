package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.common.exception.ProductImagesNotFoundException;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.product.domain.models.ProductImage;
import com.primetech.primetech_store.product.infraestructure.repositories.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductImageService implements ProductImageServiceInterface {
    private final ProductImageRepository productImageRepository;
    private final ProductService productService;

    @Override
    public void uploadProductImage(UUID productId, String url, boolean isMain) {
        Product product = productService.findProductByProductId(productId);
        productImageRepository.save(new ProductImage(product, url, isMain));
    }

    @Override
    public List<ProductImage> findProductImage(UUID productId) {
        Product product = productService.findProductByProductId(productId);
        List<ProductImage> productImages = productImageRepository.findByProduct_ProductId(product.getProductId());
        if (productImages.isEmpty()) {
            throw new ProductImagesNotFoundException("Product images not found");
        }
        return productImages;
    }

    @Override
    public void deleteProductImage(UUID productImageId) {
        Optional<ProductImage> productImageOptional = productImageRepository.findByProductImageId(productImageId);
        if (productImageOptional.isEmpty()) {
            throw new ProductImagesNotFoundException("Product image noy found");
        }
        ProductImage productImage = productImageOptional.get();
        productImageRepository.delete(productImage);
    }
}
