package com.primetech.primetech_store.product.infraestructure.repositories;

import com.primetech.primetech_store.product.domain.models.SimCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SimCardRepository extends JpaRepository<SimCard, UUID> {
    boolean existsByMobileDevice_MobileDeviceId(UUID mobileDeviceId);
    List<SimCard> findByMobileDevice_MobileDeviceId(UUID mobileDevice);
}
