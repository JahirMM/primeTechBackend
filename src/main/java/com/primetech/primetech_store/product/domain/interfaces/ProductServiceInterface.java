package com.primetech.primetech_store.product.domain.interfaces;

import com.primetech.primetech_store.product.domain.models.Product;

import java.util.UUID;

public interface ProductServiceInterface {
    Product saveProduct(Product product);
    Product findProductByProductId(UUID productId);
    boolean existsProductByProductId(UUID productId);
}
