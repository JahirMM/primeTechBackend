package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.AddLaptopApplication;
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
@RequestMapping("/prime-tech/api/v1/laptop")
@RequiredArgsConstructor
public class LaptopController {
    private final AddLaptopApplication addLaptopApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddLaptopResponsiveDTO> addLaptop(@Valid @RequestBody AddLaptopRequestDTO request, @PathVariable UUID productId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            LaptopDTO laptopDTO = addLaptopApplication.addLaptopApplication(request, productId, authentication.getName());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new AddLaptopResponsiveDTO("Laptop successfully added", laptopDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddLaptopResponsiveDTO("Please log in", null));
        }
    }
}
