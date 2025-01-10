package com.primetech.primetech_store.Offer.infraestructure.controllers;

import com.primetech.primetech_store.Offer.application.AddOfferApplication;
import com.primetech.primetech_store.Offer.application.DTO.AddOfferResponseDTO;
import com.primetech.primetech_store.Offer.application.DTO.GetOfferResponseDTO;
import com.primetech.primetech_store.Offer.application.DTO.OfferDTO;
import com.primetech.primetech_store.Offer.application.DTO.OfferRequestDTO;
import com.primetech.primetech_store.Offer.application.GetOfferApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/offers")
@RequiredArgsConstructor
public class OfferController {
    private final AddOfferApplication addOfferApplication;
    private final GetOfferApplication getOfferApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddOfferResponseDTO> addOffer(@Valid @RequestBody OfferRequestDTO request, @PathVariable UUID productId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            OfferDTO offer = addOfferApplication.addOffer(request, authentication.getName(), productId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new AddOfferResponseDTO("Offer successfully added", offer));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddOfferResponseDTO("Please log in", null));
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetOfferResponseDTO> getOffer(@PathVariable UUID productId) {
        OfferDTO offer = getOfferApplication.getOffer(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetOfferResponseDTO(offer));
    }
}
