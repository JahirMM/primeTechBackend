package com.primetech.primetech_store.user.infraestructure.services;

import com.primetech.primetech_store.common.application.exception.UserNotFoundException;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.infraestructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    @Override
    public User findUserInformationByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public User findUserInformationByUserId(UUID userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: "));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
