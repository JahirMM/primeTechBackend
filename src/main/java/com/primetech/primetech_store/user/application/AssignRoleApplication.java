package com.primetech.primetech_store.user.application;

import com.primetech.primetech_store.user.application.dto.UserDTO;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.models.UserRoleAssignment;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class AssignRoleApplication {
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;

    @Transactional
    public UserDTO assignRole(String email, String rolName) {
        UserRoleAssignment userRoleAssignment = userRoleAssignmentService.saveUserRoleAssignment(email, rolName);
        List<UUID> associatedRoles = userRoleAssignmentService.findAssignedRolesByUserId(userRoleAssignment.getUser().getUserId());
        List<String> roleNames = userRoleAssignmentService.findRoleNamesByRoleIds(associatedRoles);
        return new UserDTO(userRoleAssignment.getUser(), roleNames);
    }
}
