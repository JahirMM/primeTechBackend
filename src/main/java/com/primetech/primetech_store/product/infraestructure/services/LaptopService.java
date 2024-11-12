package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.product.domain.interfaces.LaptopServiceInterface;
import com.primetech.primetech_store.product.domain.models.Laptop;
import com.primetech.primetech_store.product.infraestructure.repositories.LaptopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LaptopService implements LaptopServiceInterface {
    private final LaptopRepository laptopRepository;

    @Override
    public Laptop saveLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    @Override
    public List<Laptop> findLaptopInformationByDeviceId(UUID deviceId) {
        return laptopRepository.findByDevice_DeviceId(deviceId);
    }
}
