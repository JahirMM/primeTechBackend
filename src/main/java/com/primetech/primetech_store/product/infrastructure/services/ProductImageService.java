package com.primetech.primetech_store.product.infrastructure.services;

import com.primetech.primetech_store.common.application.exception.ProductImagesNotFoundException;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.product.domain.models.ProductImage;
import com.primetech.primetech_store.product.infrastructure.repositories.ProductImageRepository;
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
        return productImageRepository.findByProduct_ProductId(product.getProductId());
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

    @Override
    public int countProductImageByProductId(UUID productId) {
        Product product = productService.findProductByProductId(productId);
        return productImageRepository.countByProduct_ProductId(product.getProductId());
    }

    @Override
    public boolean existsProductByProductIdAndMainTrue(UUID productId) {
        Product product = productService.findProductByProductId(productId);
        return productImageRepository.existsByProduct_ProductIdAndMainTrue(product.getProductId());
    }

    @Override
    public ProductImage findProductImageByProductImageId(UUID productImageId) {
        Optional<ProductImage> productImageOptional = productImageRepository.findByProductImageId(productImageId);
        if (productImageOptional.isEmpty()) {
            throw new ProductImagesNotFoundException("Product image not found");
        }
        return productImageOptional.get();
    }

    @Override
    public ProductImage findProductImagByProductIdAndMainTrue(UUID productId) {
        return productImageRepository.findByProduct_ProductIdAndMainTrue(productId).orElse(null);
    }
}
