package com.primetech.primetech_store.user.infraestructure.controllers;


import com.primetech.primetech_store.common.exception.RoleNotFoundException;
import com.primetech.primetech_store.common.exception.UserAlreadyHasRoleException;
import com.primetech.primetech_store.common.exception.UserNotFoundException;
import com.primetech.primetech_store.common.exception.UserRoleAssignmentNotFoundException;
import com.primetech.primetech_store.user.application.AssignRoleApplication;
import com.primetech.primetech_store.user.application.DeleteAssignedRoleApplication;
import com.primetech.primetech_store.user.application.dto.AssignRoleResponseDTO;
import com.primetech.primetech_store.user.application.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final AssignRoleApplication assignRoleApplication;
    private final DeleteAssignedRoleApplication deleteAssignedRoleApplication;

    @PostMapping("/assign/{roleName}")
    public ResponseEntity<AssignRoleResponseDTO> assignRole(@PathVariable String roleName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            try {
                String email = authentication.getName();
                UserDTO userDTO = assignRoleApplication.assignRole(email, roleName);
                return ResponseEntity.ok(new AssignRoleResponseDTO(userDTO, "correctly assigned role"));
            } catch (UserAlreadyHasRoleException ex) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new AssignRoleResponseDTO(null, ex.getMessage()));
            } catch (UserNotFoundException | RoleNotFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new AssignRoleResponseDTO(null, ex.getMessage()));
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new AssignRoleResponseDTO(null, ex.getMessage()));
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AssignRoleResponseDTO(null, "Please log in"));
        }
    }

    @DeleteMapping("/{roleName}")
    public ResponseEntity<Map<String, String>> deleteAssignedRole(@PathVariable String roleName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> response = new HashMap<>();
        if (authentication != null && authentication.isAuthenticated()) {
            try {
                String email = authentication.getName();
                deleteAssignedRoleApplication.deleteAssignedRole(email, roleName);
                response.put("message", "The assigned role has been successfully deleted");
                return ResponseEntity.ok(response);
            } catch (UserRoleAssignmentNotFoundException | UserNotFoundException | RoleNotFoundException ex) {
                response.put("message", ex.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(response);
            } catch (RuntimeException ex) {
                response.put("message", ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(response);
            }
        } else {
            response.put("message", "Please log in");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }
}
