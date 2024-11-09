package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.AddCameraApplication;
import com.primetech.primetech_store.product.application.AddScreenApplication;
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
@RequestMapping("/prime-tech/api/v1/screen")
@RequiredArgsConstructor
public class ScreenController {
    private final AddScreenApplication addMobileDeviceApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddScreenResponseDTO> addScreen(@Valid @RequestBody AddScreenRequestDTO request, @PathVariable UUID productId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            ScreenDTO screenDTO = addMobileDeviceApplication.addScreenApplication(request, authentication.getName(), productId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new AddScreenResponseDTO("Screen successfully added", screenDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddScreenResponseDTO("Please log in", null));
        }
    }
}
