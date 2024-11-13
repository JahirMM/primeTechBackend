package com.primetech.primetech_store.product.application.DTO.battery;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.primetech.primetech_store.common.StringDeserializer;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatteryRequestDTO {
    @NotBlank(message = "Capacity is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 10, message = "Capacity can have a maximum of 10 characters")
    @Pattern(regexp = "^[0-9]+\\s?mAh$", message = "Capacity must be a number followed by 'mAh' (e.g., '5000 mAh')")
    private String capacity;

    @NotBlank(message = "Type is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 30, message = "Type can have a maximum of 30 characters")
    private String type;

    @NotNull(message = "Wireless charging is mandatory")
    private Boolean wirelessCharging;

    @NotNull(message = "Fast charging is mandatory")
    private Boolean fastCharging;

    @NotNull(message = "Max battery duration is mandatory")
    @DecimalMin(value = "0.1", message = "Max battery duration must be at least 0.1 hours")
    @DecimalMax(value = "100.0", message = "Max battery duration can have a maximum of 100.0 hours")
    private BigDecimal maxBatteryDuration;
}
