package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.AddMobileDeviceApplication;
import com.primetech.primetech_store.product.application.AddSimCardApplication;
import com.primetech.primetech_store.product.application.DTO.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/sim-card")
@RequiredArgsConstructor
public class SimCardController {
    private final AddSimCardApplication simCardApplication;

    @PostMapping("/{mobileDeviceId}")
    public ResponseEntity<AddSimCardResponseDTO> addSimCard(@PathVariable UUID mobileDeviceId, @Valid @RequestBody AddSimCardRequestDTO request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            SimCardDTO simCardDTO = simCardApplication.addSimCardApplication(request, mobileDeviceId, authentication.getName());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new AddSimCardResponseDTO("Sim card successfully added", simCardDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddSimCardResponseDTO("Please log in", null));
        }
    }
}
