package com.primetech.primetech_store.review.domain.interfaces;

import java.util.UUID;

public interface ReviewServicesInterface {
    Double findAverageRatingByProductId(UUID productId);
}
