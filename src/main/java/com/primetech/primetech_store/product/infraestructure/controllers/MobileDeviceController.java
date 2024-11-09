package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.AddMobileDeviceApplication;
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
@RequestMapping("/prime-tech/api/v1/mobile-device")
@RequiredArgsConstructor
public class MobileDeviceController {
    private final AddMobileDeviceApplication addMobileDeviceApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddMobileDeviceResponseDTO> addMobileDevice(@PathVariable UUID productId, @Valid @RequestBody AddMobileDeviceRequestDTO request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            MobileDeviceDTO mobileDevice = addMobileDeviceApplication.addMobileDeviceApplication(request, productId, authentication.getName());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new AddMobileDeviceResponseDTO("Product successfully added", mobileDevice));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddMobileDeviceResponseDTO("Please log in", null));
        }
    }
}
