package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.product.application.DTO.product.ProductDetailsDTO;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
public class GetProductsApplication {
    private final ProductServiceInterface productService;
    private final DeviceServiceInterface deviceService;


    @Transactional
    public Page<ProductDetailsDTO> getProductsApplication(String name, String brand, UUID categoryId, UUID sellerId, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        Page<Product> products = productService.findAllProducts(name, brand, categoryId, sellerId, minPrice, maxPrice, pageable);
        return products.map(product -> {
            Device device = deviceService.findDevicebyProductId(product.getProductId());
            return new ProductDetailsDTO(product, device.getDeviceType().getTypeName());
        });
    }
}
