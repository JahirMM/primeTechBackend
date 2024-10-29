package com.primetech.primetech_store.user.application;

import com.primetech.primetech_store.user.application.dto.UserDTO;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.models.UserRoleAssignment;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class AssignRoleApplication {
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;

    public UserDTO assignRole(UUID userId, String rolName) {
        UserRoleAssignment userRoleAssignment = userRoleAssignmentService.saveUserRoleAssignment(userId, rolName);
        List<UUID> associatedRoles = userRoleAssignmentService.findAssignedRolesByUserId(userId);
        List<String> roleNames = userRoleAssignmentService.findRoleNamesByRoleIds(associatedRoles);
        return new UserDTO(userRoleAssignment.getUser(), roleNames);
    }
}
