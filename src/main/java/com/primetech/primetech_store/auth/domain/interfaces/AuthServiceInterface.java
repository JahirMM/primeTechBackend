package com.primetech.primetech_store.auth.domain.interfaces;

import com.primetech.primetech_store.auth.application.dto.LoginRequestDTO;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.domain.models.UserRole;
import com.primetech.primetech_store.user.domain.models.UserRoleAssignment;

public interface AuthServiceInterface {
    User findUserByEmail(LoginRequestDTO request);
    User createUser(User user);
    UserRole findRoleByRoleName(String roleName);
    UserRoleAssignment saveAssignedRole(UserRoleAssignment userRoleAssignment);
    boolean emailExists(String email);
}
