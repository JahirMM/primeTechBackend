package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.application.exception.ProductNotFoundException;
import com.primetech.primetech_store.product.application.DTO.laptop.LaptopDTO;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.LaptopServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.Laptop;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GetLaptopApplication {
    private final LaptopServiceInterface laptopService;
    private final DeviceServiceInterface deviceService;
    private final ProductServiceInterface productService;

    public List<LaptopDTO> getLaptopApplication(UUID productId){
        if(!productService.existsProductByProductId(productId)) {
            throw new ProductNotFoundException("Product not found");
        }
        Device device = deviceService.findDevicebyProductId(productId);
        List<Laptop> laptops = laptopService.findLaptopInformationByDeviceId(device.getDeviceId());
        return laptops.stream().map(LaptopDTO::from).collect(Collectors.toList());
    }
}
