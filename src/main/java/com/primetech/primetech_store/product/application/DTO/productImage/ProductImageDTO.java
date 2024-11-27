package com.primetech.primetech_store.product.application.DTO.productImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDTO {
    private UUID productImageId;
    private String imageUrl;
    private boolean isMain;
}
