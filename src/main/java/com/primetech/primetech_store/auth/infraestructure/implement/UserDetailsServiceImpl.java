package com.primetech.primetech_store.auth.infraestructure.implement;

import com.primetech.primetech_store.auth.domain.interfaces.UserDetailsInterface;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.infraestructure.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsInterface {
    @Override
    public UserDetails createUserDetails(User user) {
        return new CustomUserDetails(user);
    }
}
