package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.AddScreenApplication;
import com.primetech.primetech_store.product.application.DTO.screen.*;
import com.primetech.primetech_store.product.application.GetScreenApplication;
import com.primetech.primetech_store.product.application.UpdateScreenApplication;
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
@RequestMapping("/prime-tech/api/v1/screen")
@RequiredArgsConstructor
public class ScreenController {
    private final AddScreenApplication addMobileDeviceApplication;
    private final GetScreenApplication getScreenApplication;
    private final UpdateScreenApplication updateScreenApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddScreenResponseDTO> addScreen(@Valid @RequestBody ScreenRequestDTO request, @PathVariable UUID productId){
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

    @GetMapping("/{productId}")
    public ResponseEntity<GetScreenResponseDTO> getScreen(@PathVariable UUID productId) {
        List<ScreenDTO> screens = getScreenApplication.getCameraApplication(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetScreenResponseDTO(screens));
    }

    @PutMapping("/{screenId}")
    public ResponseEntity<UpdateScreenResponseDTO> updateScreen(@Valid @RequestBody ScreenRequestDTO request, @PathVariable UUID screenId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            ScreenDTO screenDTO = updateScreenApplication.updateScreenApplication(authentication.getName(), screenId, request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UpdateScreenResponseDTO("Screen successfully updated", screenDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UpdateScreenResponseDTO("Please log in", null));
        }
    }
}
