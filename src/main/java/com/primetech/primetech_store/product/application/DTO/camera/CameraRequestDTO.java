package com.primetech.primetech_store.product.application.DTO.camera;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.primetech.primetech_store.common.StringDeserializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CameraRequestDTO {
    @NotBlank(message = "Type is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 5, message = "Camera type can have a maximum of 5 characters")
    @Pattern(regexp = "^(front|rear)$", message = "Camera type must be either 'front' or 'rear'")
    private String type;

    @NotBlank(message = "Resolution memory is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 15, message = "Resolution can have a maximum of 15 characters")
    private String resolution;

    @NotBlank(message = "Aperture memory is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 10, message = "Aperture can have a maximum of 10 characters")
    private String aperture;

    @NotBlank(message = "Optical zoom memory is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 10, message = "Optical zoom can have a maximum of 10 characters")
    private String opticalZoom;

    @NotBlank(message = "Digital zoom memory is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 10, message = "Digital zoom can have a maximum of 10 characters")
    private String digitalZoom;

    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 385, message = "Feature can have a maximum of 385 characters")
    private String feature;
}
