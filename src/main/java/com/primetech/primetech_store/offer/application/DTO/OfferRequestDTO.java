package com.primetech.primetech_store.offer.application.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequestDTO {
    @NotNull(message = "The discount percentage cannot be zero")
    @DecimalMin(value = "0.0", inclusive = false, message = "The discount percentage must be greater than 0")
    private BigDecimal discountPercentage;

    @NotNull(message = "The start date cannot be null and void")
    private LocalDateTime startDate;

    @NotNull(message = "The termination date cannot be null and void")
    private LocalDateTime endDate;
}