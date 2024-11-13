package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.AddMobileDeviceApplication;
import com.primetech.primetech_store.product.application.DTO.mobileDevice.*;
import com.primetech.primetech_store.product.application.GetMobileDeviceApplication;
import com.primetech.primetech_store.product.application.UpdateMobileDeviceApplication;
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
@RequestMapping("/prime-tech/api/v1/mobile-device")
@RequiredArgsConstructor
public class MobileDeviceController {
    private final AddMobileDeviceApplication addMobileDeviceApplication;
    private final GetMobileDeviceApplication getMobileDeviceApplication;
    private final UpdateMobileDeviceApplication updateMobileDeviceApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddMobileDeviceResponseDTO> addMobileDevice(@PathVariable UUID productId, @Valid @RequestBody MobileDeviceRequestDTO request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            MobileDeviceDTO mobileDevice = addMobileDeviceApplication.addMobileDeviceApplication(request, productId, authentication.getName());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new AddMobileDeviceResponseDTO("Mobile device successfully added", mobileDevice));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddMobileDeviceResponseDTO("Please log in", null));
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetMobileDeviceApplicationDTO> getMobileDevice(@PathVariable UUID productId) {
        List<MobileDeviceDTO> mobileDevices = getMobileDeviceApplication.getMobileDeviceApplication(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetMobileDeviceApplicationDTO(mobileDevices));
    }

    @PutMapping("/{mobileDeviceId}")
    public ResponseEntity<UpdateMobileDeviceResponseDTO> updateMobileDevice(@PathVariable UUID mobileDeviceId, @Valid @RequestBody MobileDeviceRequestDTO request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            MobileDeviceDTO mobileDevice = updateMobileDeviceApplication.updateMobileDeviceApplication(authentication.getName(), mobileDeviceId, request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UpdateMobileDeviceResponseDTO("Mobile device successfully updated ", mobileDevice));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UpdateMobileDeviceResponseDTO("Please log in", null));
        }
    }
}
