package com.primetech.primetech_store.product.domain.interfaces;

import java.util.UUID;

public interface ProductRelationshipServiceInterface {
    void deleteProductRelationships(UUID deviceId, UUID productId);
}
