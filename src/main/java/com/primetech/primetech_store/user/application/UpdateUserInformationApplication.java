package com.primetech.primetech_store.user.application;

import com.primetech.primetech_store.common.application.exception.InvalidFieldFormatException;
import com.primetech.primetech_store.user.application.dto.UpdateUserInformationRequestDTO;
import com.primetech.primetech_store.user.application.dto.UserDTO;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class UpdateUserInformationApplication {
    private final UserServiceInterface userService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;

    private void validateRequestFields(UpdateUserInformationRequestDTO request) {
        if (!request.getFirstName().matches("^[a-zA-Z\\s]+$")) {
            throw new InvalidFieldFormatException("First name must contain only letters and spaces");
        }
        if (request.getMiddleName() != null && !request.getMiddleName().matches("^[a-zA-Z\\s]*$")) {
            throw new InvalidFieldFormatException("Middle name must contain only letters and spaces");
        }
        if (!request.getPaternalSurname().matches("^[a-zA-Z\\s]+$")) {
            throw new InvalidFieldFormatException("Paternal surname must contain only letters and spaces");
        }
        if (!request.getMaternalSurname().matches("^[a-zA-Z\\s]+$")) {
            throw new InvalidFieldFormatException("Maternal surname must contain only letters and spaces");
        }
    }

    @Transactional
    public UserDTO updateUserInformation(UpdateUserInformationRequestDTO updateUserInformationRequest, Authentication authentication) {
        validateRequestFields(updateUserInformationRequest);

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

        List<UUID> associatedRoles = userRoleAssignmentService.findAssignedRolesByUserId(updatedUser.getUserId());
        List<String> roleNames = userRoleAssignmentService.findRoleNamesByRoleIds(associatedRoles);
        return new UserDTO(updatedUser, roleNames);
    }
}
