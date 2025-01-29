package com.primetech.primetech_store.product.application.DTO.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserProductsResponseDTO {
    private String message;
    private List<UserProductDTO> product;
}
