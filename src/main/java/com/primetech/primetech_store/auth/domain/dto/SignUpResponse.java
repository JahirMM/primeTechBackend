package com.primetech.primetech_store.auth.domain.dto;

import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponse {
    String message;
    User user;
    String token;
}
