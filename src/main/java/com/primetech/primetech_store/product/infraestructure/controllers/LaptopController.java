package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.AddLaptopApplication;
import com.primetech.primetech_store.product.application.DTO.laptop.*;
import com.primetech.primetech_store.product.application.GetLaptopApplication;
import com.primetech.primetech_store.product.application.UpdateLaptopApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/laptop")
@RequiredArgsConstructor
public class LaptopController {
    private final AddLaptopApplication addLaptopApplication;
    private final GetLaptopApplication getLaptopApplication;
    private final UpdateLaptopApplication updateLaptopApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddLaptopResponsiveDTO> addLaptop(@Valid @RequestBody LaptopRequestDTO request, @PathVariable UUID productId){
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

    @GetMapping("/{productId}")
    public ResponseEntity<GetLaptopResponsiveDTO> getLaprop(@PathVariable UUID productId) {
        List<LaptopDTO> laptop = getLaptopApplication.getLaptopApplication(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetLaptopResponsiveDTO(laptop));
    }

    @PutMapping("/{laptopId}")
    public ResponseEntity<UpdateLaptopResponseDTO> updateLaptop(@Valid @RequestBody LaptopRequestDTO request, @PathVariable UUID laptopId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            LaptopDTO laptopDTO = updateLaptopApplication.updateLaptopApplication(authentication.getName(), laptopId, request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UpdateLaptopResponseDTO("Laptop successfully updated", laptopDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UpdateLaptopResponseDTO("Please log in", null));
        }
    }
}
