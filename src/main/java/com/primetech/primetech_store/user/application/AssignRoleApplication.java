package com.primetech.primetech_store.user.application;

import com.primetech.primetech_store.user.application.dto.UserDTO;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.UserRoleAssignment;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class AssignRoleApplication {
    private final UserServiceInterface userService;

    public UserDTO assignRole(UUID userId, String rolName) {
        UserRoleAssignment userRoleAssignment = userService.saveUserRoleAssignment(userId, rolName);
        List<UUID> associatedRoles = userService.findAssignedRolesByUserId(userId);
        List<String> roleNames = userService.findRoleNamesByRoleIds(associatedRoles);
        return new UserDTO(userRoleAssignment.getUser(), roleNames);
    }
}
