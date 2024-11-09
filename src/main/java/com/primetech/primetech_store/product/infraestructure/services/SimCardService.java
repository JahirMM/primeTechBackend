package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.product.domain.interfaces.SimCardServiceInterface;
import com.primetech.primetech_store.product.domain.models.SimCard;
import com.primetech.primetech_store.product.infraestructure.repositories.SimCardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SimCardService implements SimCardServiceInterface {
    private SimCardRepository simCardRepository;

    @Override
    public SimCard saveSimCard(SimCard simCard) {
        return simCardRepository.save(simCard);
    }

    @Override
    public boolean existsSimCardByMobileDeviceId(UUID mobileDeviceId) {
        return simCardRepository.existsByMobileDevice_MobileDeviceId(mobileDeviceId);
    }
}
