package com.primetech.primetech_store.user.domain.interfaces;

import com.primetech.primetech_store.user.domain.models.User;

import java.util.UUID;

public interface UserServiceInterface {
    User findUserInformationByEmail(String email);
    User findUserInformationByUserId(UUID userId);
    User saveUser(User user);
}
