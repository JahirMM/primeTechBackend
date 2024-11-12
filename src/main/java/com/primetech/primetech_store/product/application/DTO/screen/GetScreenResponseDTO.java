package com.primetech.primetech_store.product.application.DTO.screen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetScreenResponseDTO {
    private List<ScreenDTO> screen;
}
