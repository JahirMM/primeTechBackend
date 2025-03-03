package com.primetech.primetech_store.offer.infrastructure.controllers;

import com.primetech.primetech_store.offer.application.AddOfferApplication;
import com.primetech.primetech_store.offer.application.ChangeOfferStatusApplication;
import com.primetech.primetech_store.offer.application.DTO.*;
import com.primetech.primetech_store.offer.application.GetOfferApplication;
import com.primetech.primetech_store.offer.application.UpdateOfferApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/offers")
@RequiredArgsConstructor
public class OfferController {
    private final AddOfferApplication addOfferApplication;
    private final GetOfferApplication getOfferApplication;
    private final UpdateOfferApplication updateOfferApplication;
    private final ChangeOfferStatusApplication changeOfferStatusApplication;

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

    @PutMapping("/{offerId}")
    public ResponseEntity<UpdateOfferResponseDTO> updateOffer(@Valid @RequestBody OfferRequestDTO request, @PathVariable UUID offerId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            OfferDTO offer = updateOfferApplication.updateOffer(offerId, request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UpdateOfferResponseDTO("Offer correctly updated and active", offer));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UpdateOfferResponseDTO("Please log in", null));
        }
    }

    @PatchMapping("/{offerId}/activate")
    public ResponseEntity<Map<String, String>> activateOffer(@PathVariable UUID offerId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String message = changeOfferStatusApplication.changeOfferStatus(offerId, true);
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", message);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Unauthorized access.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
        }
    }

    @PatchMapping("/{offerId}/deactivate")
    public ResponseEntity<Map<String, String>> deactivateOffer(@PathVariable UUID offerId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String message = changeOfferStatusApplication.changeOfferStatus(offerId, false);
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", message);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Unauthorized access.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
        }
    }
}
