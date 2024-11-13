package com.primetech.primetech_store.product.domain.interfaces;

import com.primetech.primetech_store.product.domain.models.Laptop;

import java.util.List;
import java.util.UUID;

public interface LaptopServiceInterface {
    Laptop saveLaptop(Laptop laptop);
    List<Laptop> findLaptopInformationByDeviceId(UUID deviceId);
    Laptop findLaptopByLaptopId(UUID laptopId);
}
