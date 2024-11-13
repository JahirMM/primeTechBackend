package com.primetech.primetech_store.product.application.DTO.mobileDevice;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.primetech.primetech_store.common.StringDeserializer;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileDeviceRequestDTO {

    @NotNull(message = "Internal memory is mandatory")
    @Min(value = 1, message = "Internal memory must be at least 1 GB")
    private int internalMemory;

    @NotBlank(message = "Internal memory type is mandatory")
    @Size(max = 5, message = "Internal memory type can have a maximum of 5 characters")
    @JsonDeserialize(using = StringDeserializer.class)
    private String internalMemoryType;

    @NotNull(message = "RAM is mandatory")
    @Min(value = 1, message = "RAM must be at least 1 GB")
    private int ram;

    @NotBlank(message = "Color is mandatory")
    @Size(max = 30, message = "Color can have a maximum of 30 characters")
    @JsonDeserialize(using = StringDeserializer.class)
    private String color;

    @NotBlank(message = "Processor is mandatory")
    @Size(max = 50, message = "Processor can have a maximum of 50 characters")
    @JsonDeserialize(using = StringDeserializer.class)
    private String processor;

    @NotBlank(message = "Operating system is mandatory")
    @Size(max = 30, message = "Operating system can have a maximum of 30 characters")
    @JsonDeserialize(using = StringDeserializer.class)
    private String operatingSystem;

    @Size(max = 10, message = "IP rating can have a maximum of 10 characters")
    @JsonDeserialize(using = StringDeserializer.class)
    private String ipRating;

    @NotNull(message = "Splash resistance is mandatory")
    private boolean splashResistant;

    @NotNull(message = "Dust resistance is mandatory")
    private boolean dustResistant;

    @NotNull(message = "Water resistance is mandatory")
    private boolean waterResistant;
}

