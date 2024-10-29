package com.primetech.primetech_store.user.application;

import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class DeleteAssignedRoleApplication {
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;

    public void deleteAssignedRole(UUID userId, String roleName) {
        userRoleAssignmentService.deleteAssignedRole(userId, roleName);
    }
}