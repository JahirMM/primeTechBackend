package com.primetech.primetech_store.user.application;

import com.primetech.primetech_store.user.application.dto.UserDTO;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class GetUserInformationApplication {
    private final UserServiceInterface userService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;

    @Transactional
    public UserDTO getUserInformation(String email) {
        User user = userService.findUserInformationByEmail(email);
        if (user != null) {
            List<UUID> associatedRoles = userRoleAssignmentService.findAssignedRolesByUserId(user.getUserId());
            List<String> roleNames = userRoleAssignmentService.findRoleNamesByRoleIds(associatedRoles);
            UserDTO userDTO = new UserDTO(user, roleNames);
            return userDTO;
        }
        return null;
    }
}