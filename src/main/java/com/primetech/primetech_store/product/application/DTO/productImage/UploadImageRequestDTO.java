package com.primetech.primetech_store.product.application.DTO.productImage;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadImageRequestDTO {
    @NotNull(message = "Indicate whether the image is main or not")
    private boolean isMain;
}
