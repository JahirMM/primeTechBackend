package com.primetech.primetech_store.user.application;

import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class DeleteAssignedRoleApplication {
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;

    @Transactional
    public void deleteAssignedRole(String email, String roleName) {
        userRoleAssignmentService.deleteAssignedRole(email, roleName);
    }
}