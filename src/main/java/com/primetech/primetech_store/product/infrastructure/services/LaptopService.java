package com.primetech.primetech_store.product.infrastructure.services;

import com.primetech.primetech_store.common.application.exception.ProductNotFoundException;
import com.primetech.primetech_store.product.domain.interfaces.LaptopServiceInterface;
import com.primetech.primetech_store.product.domain.models.Laptop;
import com.primetech.primetech_store.product.infrastructure.repositories.LaptopRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Laptop findLaptopByLaptopId(UUID laptopId) {
        Optional<Laptop> laptopOptional = laptopRepository.findByLaptopId(laptopId);
        if (laptopOptional.isEmpty()) {
            throw new ProductNotFoundException("Laptop noy found");
        }

        return laptopOptional.get();
    }

    @Override
    public void deleteLaptopByLaptopId(UUID laptopId) {
        Optional<Laptop> laptopOptional = laptopRepository.findByLaptopId(laptopId);
        if (laptopOptional.isEmpty()) {
            throw new ProductNotFoundException("Laptop noy found");
        }

        laptopRepository.deleteByLaptopId(laptopId);
    }
}
