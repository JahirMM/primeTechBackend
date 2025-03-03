package com.primetech.primetech_store.filter.infrastructure.controllers;

import com.primetech.primetech_store.filter.application.DTO.FilterResponseDTO;
import com.primetech.primetech_store.filter.application.GetFilterDataApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/filter-data")
@RequiredArgsConstructor
public class FilterDataController {
    private final GetFilterDataApplication getFilterDataApplication;

    @GetMapping("")
    public ResponseEntity<FilterResponseDTO> getFilterData(
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Double minRating) {

        brand = (brand == null) ? "" : brand;

        UUID categoryUUID = null;
        try {
            if (categoryId != null) {
                categoryUUID = UUID.fromString(categoryId);
            }
        } catch (IllegalArgumentException e) {
            FilterResponseDTO emptyResponse = new FilterResponseDTO(
                    null,
                    minPrice,
                    maxPrice,
                    Collections.emptyList()
            );
            return ResponseEntity.ok(emptyResponse);
        }

        FilterResponseDTO response = getFilterDataApplication.getFilterData(categoryUUID, brand, minPrice, maxPrice, minRating);
        return ResponseEntity.ok(response);
    }
}

