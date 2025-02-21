package com.primetech.primetech_store.product.application.DTO.product;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.primetech.primetech_store.common.StringDeserializer;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name can have a maximum of 100 characters")
    @JsonDeserialize(using = StringDeserializer.class)
    private String name;

    @Size(max = 700, message = "Description can have a maximum of 700 characters")
    @JsonDeserialize(using = StringDeserializer.class)
    private String description;

    @NotBlank(message = "Brand is mandatory")
    @Size(max = 50, message = "Brand can have a maximum of 50 characters")
    @JsonDeserialize(using = StringDeserializer.class)
    private String brand;

    @NotNull(message = "Stock is mandatory")
    @Min(value = 0, message = "Stock cannot be less than 0")
    private int stock;

    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price cannot be less than 0")
    private BigDecimal price;

    @NotBlank(message = "Category is mandatory")
    @Size(max = 50, message = "Category can have a maximum of 50 characters")
    private String category;
}

