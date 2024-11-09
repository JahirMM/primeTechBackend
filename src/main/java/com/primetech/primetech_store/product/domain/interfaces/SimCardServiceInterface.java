package com.primetech.primetech_store.product.domain.interfaces;

import com.primetech.primetech_store.product.domain.models.SimCard;

import java.util.UUID;

public interface SimCardServiceInterface {
    SimCard saveSimCard(SimCard simCard);
    boolean existsSimCardByMobileDeviceId(UUID mobileDeviceId);
}
