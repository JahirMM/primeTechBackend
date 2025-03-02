package com.primetech.primetech_store.recentProducts.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRecentProductRequestDTO {
    private String favoriteProductId;
}
