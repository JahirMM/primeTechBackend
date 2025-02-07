package com.primetech.primetech_store.sellerInformation.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerInformationDTO {
    private String firstName;
    private String paternalSurname;
    private String imageUrl;
    private long quantityProducts;
}
