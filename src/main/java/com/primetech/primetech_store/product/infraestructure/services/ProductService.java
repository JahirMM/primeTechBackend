package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.common.exception.ProductNotFoundException;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.product.infraestructure.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServiceInterface {
    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProductByProductId(UUID productId) {
        Optional<Product> productOptional = productRepository.findProductByProductId(productId);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }

        return productOptional.get();
    }

    @Override
    public boolean existsProductByProductId(UUID productId) {
        return productRepository.existsByProductId(productId);
    }
}
