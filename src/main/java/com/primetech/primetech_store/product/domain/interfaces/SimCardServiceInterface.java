package com.primetech.primetech_store.product.domain.interfaces;

import com.primetech.primetech_store.product.domain.models.SimCard;

import java.util.List;
import java.util.UUID;

public interface SimCardServiceInterface {
    SimCard saveSimCard(SimCard simCard);
    boolean existsSimCardByMobileDeviceId(UUID mobileDeviceId);
    List<SimCard> findSimCardInformationByMobileDevice(UUID mobileDevice);
    SimCard findSimCardBySimCardId(UUID simCardId);
    void deleteSimCardBySimCardId(UUID simCardId);
}
