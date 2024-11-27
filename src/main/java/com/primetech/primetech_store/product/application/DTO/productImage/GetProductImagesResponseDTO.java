package com.primetech.primetech_store.product.application.DTO.productImage;

import com.primetech.primetech_store.product.domain.models.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductImagesResponseDTO {
    private String message;
    private List<ProductImageDTO> productImages;
}
