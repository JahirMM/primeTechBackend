package com.primetech.primetech_store.product.infrastructure.services;

import com.primetech.primetech_store.common.application.exception.ProductNotFoundException;
import com.primetech.primetech_store.product.domain.interfaces.SimCardServiceInterface;
import com.primetech.primetech_store.product.domain.models.SimCard;
import com.primetech.primetech_store.product.infrastructure.repositories.SimCardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public List<SimCard> findSimCardInformationByMobileDevice(UUID mobileDevice) {
        return simCardRepository.findByMobileDevice_MobileDeviceId(mobileDevice);
    }

    @Override
    public SimCard findSimCardBySimCardId(UUID simCardId) {
        Optional<SimCard> simCardOptional = simCardRepository.findBySimCardId(simCardId);

        if (simCardOptional.isEmpty()) {
            throw new ProductNotFoundException("Sim card not found");
        }


        return simCardOptional.get();
    }

    @Override
    public void deleteSimCardBySimCardId(UUID simCardId) {
        Optional<SimCard> simCardOptional = simCardRepository.findBySimCardId(simCardId);

        if (simCardOptional.isEmpty()) {
            throw new ProductNotFoundException("Sim card not found");
        }
        simCardRepository.deleteBySimCardId(simCardId);
    }
}
