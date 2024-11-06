package com.primetech.primetech_store.user.infraestructure.controllers;

import com.primetech.primetech_store.user.application.GetUserInformationApplication;
import com.primetech.primetech_store.user.application.UpdateUserInformationApplication;
import com.primetech.primetech_store.user.application.dto.UpdateUserInformationRequestDTO;
import com.primetech.primetech_store.user.application.dto.UserDTO;
import com.primetech.primetech_store.user.application.dto.UserInformationResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prime-tech/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final GetUserInformationApplication userInformationApplication;
    private final UpdateUserInformationApplication updateUserInformationApplication;

    @GetMapping(value = "user")
    public ResponseEntity<UserInformationResponseDTO> getUserInformation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDTO userDTO = userInformationApplication.getUserInformation(authentication.getName());
            if (userDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new UserInformationResponseDTO(null, "User not found"));
            }
            return ResponseEntity.ok(new UserInformationResponseDTO(userDTO, "User found"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UserInformationResponseDTO(null, "Please log in"));
        }
    }

    @PutMapping(value = "/user")
    public ResponseEntity<UserInformationResponseDTO> updateUserInformation(@Valid @RequestBody UpdateUserInformationRequestDTO userRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
                UserDTO userDTO = updateUserInformationApplication.updateUserInformation(userRequest, authentication);
                return ResponseEntity.ok(new UserInformationResponseDTO(userDTO, "Data has been correctly updated"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UserInformationResponseDTO(null, "Please log in"));
        }
    }
}
