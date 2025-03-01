package com.primetech.primetech_store.auth.infraestructure.controllers;

import com.primetech.primetech_store.auth.application.ChangeEmailApplicaction;
import com.primetech.primetech_store.auth.application.dto.ChangeEmailRequestDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/prime-tech/api/v1/change-email")
@RequiredArgsConstructor
public class ChangeEmailController {
    private final ChangeEmailApplicaction changeEmailApplicaction;

    @PostMapping("")
    public ResponseEntity<Map<String, String>> changeEmail(@RequestBody ChangeEmailRequestDTO request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> responseData = new HashMap<>();

        if (authentication != null && authentication.isAuthenticated()) {
            String currentEmail = authentication.getName();
            changeEmailApplicaction.changeEmail(currentEmail, request.getPassword(), request.getEmail(), response);
            responseData.put("message", "Updated mail");
            return ResponseEntity.ok(responseData);
        } else {
            responseData.put("Please log in", null);
            return ResponseEntity.status(401).body(responseData);
        }
    }
}
