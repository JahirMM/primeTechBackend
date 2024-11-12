package com.primetech.primetech_store.product.application.DTO.simCard;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.primetech.primetech_store.common.StringDeserializer;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSimCardRequestDTO {
    @NotNull(message = "Dual SIM status is mandatory")
    private boolean isDualSim;

    @NotNull(message = "SIM slots count is mandatory")
    @Min(value = 1, message = "SIM slots must be at least 1")
    @Max(value = 2, message = "SIM slots can be at most 2")
    private Integer simSlots;

    @NotNull(message = "eSIM status is mandatory")
    private boolean esim;

    @NotBlank(message = "SIM type is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 15, message = "SIM type can have a maximum of 15 characters")
    private String simType;
}
