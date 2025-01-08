package com.primetech.primetech_store.review.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetReviewsResponseDTO {
    private List<ReviewDetailsDTO> reviews;
}
