package com.primetech.primetech_store.user.domain.interfaces;

import com.primetech.primetech_store.user.domain.models.User;

public interface UserServiceInterface {
    User findUserInformationByEmail(String email);
    User saveUser(User user);
}
