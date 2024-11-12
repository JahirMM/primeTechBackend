package com.primetech.primetech_store.product.application.DTO.simCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSimCardApplicationDTO {
    private List<SimCardDTO> simCard;
}
