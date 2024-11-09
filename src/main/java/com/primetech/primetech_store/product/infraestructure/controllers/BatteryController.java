package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.AddBatteryApplication;
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
@RequestMapping("/prime-tech/api/v1/battery")
@RequiredArgsConstructor
public class BatteryController {
    private final AddBatteryApplication addBatteryApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddBatteryResponseDTO> addBattery(@Valid @RequestBody AddBatteryRequestDTO request, @PathVariable UUID productId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            BatteryDTO batteryDTO = addBatteryApplication.addBatteryApplication(request, authentication.getName(), productId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new AddBatteryResponseDTO("Battery successfully added", batteryDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddBatteryResponseDTO("Please log in", null));
        }
    }
}
