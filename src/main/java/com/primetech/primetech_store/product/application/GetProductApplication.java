package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.ProductNotFoundException;
import com.primetech.primetech_store.product.application.DTO.product.ProductDTO;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class GetProductApplication {
    private final ProductServiceInterface productService;
    private final DeviceServiceInterface deviceService;

    @Transactional
    public ProductDTO getProductApplication(UUID productId) {

        if (!productService.existsProductByProductId(productId)) {
            throw new ProductNotFoundException("Product not found");
        }

        Product product = productService.findProductByProductId(productId);

        Device device = deviceService.findDevicebyProductId(product.getProductId());


        ProductDTO productDTO = new ProductDTO(
                product.getName(), product.getDescription(),
                product.getBrand(), product.getStock(),
                product.getPrice(), product.getCategory().getCategoryName(),
                device.getDeviceType().getTypeName(), product.getCreatedAt());

        return productDTO;
    }
}
