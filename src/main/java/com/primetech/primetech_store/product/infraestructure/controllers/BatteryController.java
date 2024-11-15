package com.primetech.primetech_store.product.infraestructure.controllers;

import com.primetech.primetech_store.product.application.AddBatteryApplication;
import com.primetech.primetech_store.product.application.DTO.battery.*;
import com.primetech.primetech_store.product.application.DeleteBatteryApplication;
import com.primetech.primetech_store.product.application.GetBatteryApplication;
import com.primetech.primetech_store.product.application.UpdateBatteryApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/battery")
@RequiredArgsConstructor
public class BatteryController {
    private final AddBatteryApplication addBatteryApplication;
    private final GetBatteryApplication getBatteryApplication;
    private final UpdateBatteryApplication updateBatteryApplication;
    private final DeleteBatteryApplication deleteBatteryApplication;

    @PostMapping("/{productId}")
    public ResponseEntity<AddBatteryResponseDTO> addBattery(@Valid @RequestBody BatteryRequestDTO request, @PathVariable UUID productId){
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

    @GetMapping("/{productId}")
    public ResponseEntity<GetBatteryResponseDTO> getBattery(@PathVariable UUID productId) {
        List<BatteryDTO> batteries = getBatteryApplication.getBatteryApplication(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetBatteryResponseDTO(batteries));
    }

    @PutMapping("/{batteryId}")
    public ResponseEntity<UpdateResponseDTO> updateBattery(@Valid @RequestBody BatteryRequestDTO request, @PathVariable UUID batteryId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            BatteryDTO batteryDTO = updateBatteryApplication.updateBatteryApplication(authentication.getName(), batteryId, request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UpdateResponseDTO("Battery successfully updated", batteryDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UpdateResponseDTO("Please log in", null));
        }
    }

    @DeleteMapping("/{batteryId}")
    public ResponseEntity<Map<String, String>> deleteBattery(@PathVariable UUID batteryId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> response = new HashMap<>();
        if (authentication != null && authentication.isAuthenticated()) {
            deleteBatteryApplication.deleteBatteryApplication(batteryId, authentication.getName());
            response.put("message", "Battery deleted correctly");
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(response);
        } else {
            response.put("message", "Please log iny");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }
}
