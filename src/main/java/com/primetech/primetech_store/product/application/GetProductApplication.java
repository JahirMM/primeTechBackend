package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.application.exception.ProductNotFoundException;
import com.primetech.primetech_store.product.application.DTO.product.ProductDetailsDTO;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.review.application.GetAverageRatingByProductIdApplication;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class GetProductApplication {
    private final ProductServiceInterface productService;
    private final DeviceServiceInterface deviceService;

    private final GetAverageRatingByProductIdApplication getAverageRatingByProductId;

    @Transactional
    public ProductDetailsDTO getProductApplication(UUID productId) {

        if (!productService.existsProductByProductId(productId)) {
            throw new ProductNotFoundException("Product not found");
        }

        Product product = productService.findProductByProductId(productId);

        Device device = deviceService.findDevicebyProductId(product.getProductId());

        Double averageRaiting = getAverageRatingByProductId.getAverageRatingByProductId(productId);


        return new ProductDetailsDTO(product, averageRaiting,device.getDeviceType().getTypeName());
    }
}
