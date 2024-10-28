package com.primetech.primetech_store.user.application;

import com.primetech.primetech_store.user.application.dto.UserDTO;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.UserRoleAssignment;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class AssignSellerRoleApplication {
    private final UserServiceInterface userService;

    public UserDTO assignSellerRole(UUID userId) {
        UserRoleAssignment userRoleAssignment = userService.saveUserRoleAssignment(userId, "seller");
        List<UUID> associatedRoles = userService.findAssignedRolesByUserId(userId);
        List<String> roleNames = userService.findRoleNamesByRoleIds(associatedRoles);
        return new UserDTO(userRoleAssignment.getUser(), roleNames);
    }
}
