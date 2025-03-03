package com.primetech.primetech_store.product.infrastructure.controllers;

import com.primetech.primetech_store.product.application.AddSimCardApplication;
import com.primetech.primetech_store.product.application.DTO.simCard.*;
import com.primetech.primetech_store.product.application.DeleteSimCardApplication;
import com.primetech.primetech_store.product.application.GetSimCardApplication;
import com.primetech.primetech_store.product.application.UpdateSimCardApplication;
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
@RequestMapping("/prime-tech/api/v1/sim-card")
@RequiredArgsConstructor
public class SimCardController {
    private final AddSimCardApplication simCardApplication;
    private final GetSimCardApplication getSimCardApplication;
    private final UpdateSimCardApplication updateSimCardApplication;
    private final DeleteSimCardApplication deleteSimCardApplication;

    @PostMapping("/{mobileDeviceId}")
    public ResponseEntity<AddSimCardResponseDTO> addSimCard(@PathVariable UUID mobileDeviceId, @Valid @RequestBody SimCardRequestDTO request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            SimCardDTO simCardDTO = simCardApplication.addSimCardApplication(request, mobileDeviceId, authentication.getName());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new AddSimCardResponseDTO("Sim card successfully added", simCardDTO));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AddSimCardResponseDTO("Please log in", null));
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetSimCardApplicationDTO> getSimCard(@PathVariable UUID productId){
        List<SimCardDTO> simCard = getSimCardApplication.getSimCardApplication(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetSimCardApplicationDTO(simCard));
    }

    @PutMapping("/{simCardId}")
    public ResponseEntity<UpdateSimCardResponseDTO> updateSimCard(@PathVariable UUID simCardId, @Valid @RequestBody SimCardRequestDTO request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            SimCardDTO simCard = updateSimCardApplication.updateSimCardApplication(authentication.getName(), simCardId, request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UpdateSimCardResponseDTO("Sim card successfully updated", simCard));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UpdateSimCardResponseDTO("Please log in", null));
        }
    }

    @DeleteMapping("/{simCardId}")
    public ResponseEntity<Map<String, String>> deleteSimCard(@PathVariable UUID simCardId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> response = new HashMap<>();

        if (authentication != null && authentication.isAuthenticated()) {
            deleteSimCardApplication.deleteSimCardApplication(simCardId, authentication.getName());
            response.put("message", "Sim Card deleted correctly");
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(response);
        } else {
            response.put("message", "Please log iny");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }
}
