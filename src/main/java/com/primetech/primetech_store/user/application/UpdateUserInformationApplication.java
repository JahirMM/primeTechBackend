package com.primetech.primetech_store.user.application;

import com.primetech.primetech_store.user.application.dto.UpdateUserInformationRequestDTO;
import com.primetech.primetech_store.user.application.dto.UserDTO;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class UpdateUserInformationApplication {
    private final UserServiceInterface userService;

    public UserDTO updateUserInformation(UpdateUserInformationRequestDTO updateUserInformationRequest, Authentication authentication) {
        User existingUser = userService.findUserInformationByEmail(authentication.getName());


        if (updateUserInformationRequest.getFirstName() != null && !updateUserInformationRequest.getFirstName().isEmpty()) {
            existingUser.setFirstName(updateUserInformationRequest.getFirstName());
        }
        if (updateUserInformationRequest.getMiddleName() != null) {
            existingUser.setMiddleName(updateUserInformationRequest.getMiddleName());
        }
        if (updateUserInformationRequest.getPaternalSurname() != null && !updateUserInformationRequest.getPaternalSurname().isEmpty()) {
            existingUser.setPaternalSurname(updateUserInformationRequest.getPaternalSurname());
        }
        if (updateUserInformationRequest.getMaternalSurname() != null && !updateUserInformationRequest.getMaternalSurname().isEmpty()) {
            existingUser.setMaternalSurname(updateUserInformationRequest.getMaternalSurname());
        }

        User updatedUser = userService.saveUser(existingUser);
        if (updatedUser == null) {
            throw new IllegalArgumentException("Error saving user");
        }

        List<UUID> associatedRoles = userService.findAssignedRolesByUserId(updatedUser.getUserId());
        List<String> roleNames = userService.findRoleNamesByRoleIds(associatedRoles);
        return new UserDTO(updatedUser, roleNames);
    }
}
