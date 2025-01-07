package com.primetech.primetech_store.review.application.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDTO {
    @NotNull(message = "Rating is mandatory.")
    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0.")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5.0.")
    private Double rating;

    @NotBlank(message = "Comment is mandatory.")
    @Size(max = 800, message = "Comment must not exceed 800 characters.")
    private String comment;
}
