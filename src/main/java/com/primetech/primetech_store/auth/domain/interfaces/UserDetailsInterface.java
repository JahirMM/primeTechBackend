package com.primetech.primetech_store.auth.domain.interfaces;

import com.primetech.primetech_store.user.domain.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsInterface {
    UserDetails createUserDetails(User user);
}
