package com.primetech.primetech_store.user.application;

import com.primetech.primetech_store.user.application.dto.UserDTO;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class GetUserInformationApplication {
    private final UserServiceInterface userService;

    public UserDTO getUserInformation(String email) {
        User user = userService.findUserInformationByEmail(email);
        if (user != null) {
            List<UUID> associatedRoles = userService.findAssignedRolesByUserId(user.getUserId());
            List<String> roleNames = userService.findRoleNamesByRoleIds(associatedRoles);
            UserDTO userDTO = new UserDTO(user, roleNames);
            return userDTO;
        }
        return null;
    }
}