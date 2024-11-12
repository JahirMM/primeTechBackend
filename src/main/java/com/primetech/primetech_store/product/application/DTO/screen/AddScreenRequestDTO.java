package com.primetech.primetech_store.product.application.DTO.screen;

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
public class AddScreenRequestDTO {
    @NotBlank(message = "Resolution is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 15, message = "Resolution can have a maximum of 15 characters")
    private String resolution;

    @NotBlank(message = "Pixel density is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 10, message = "Pixel density can have a maximum of 10 characters")
    private String pixelDensity;

    @NotBlank(message = "Refresh rate is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 5, message = "Refresh rate can have a maximum of 5 characters")
    @Pattern(regexp = "^[0-9]+Hz$", message = "Refresh rate must be a number followed by 'Hz' (e.g., '60Hz')")
    private String refreshRate;

    @NotBlank(message = "Screen type is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 20, message = "Screen type can have a maximum of 20 characters")
    private String screenType;

    @NotNull(message = "Screen size is mandatory")
    @DecimalMin(value = "0.1", message = "Screen size must be at least 0.1")
    @DecimalMax(value = "100.0", message = "Screen size can have a maximum of 100.0")
    private BigDecimal screenSize;
}
