package com.primetech.primetech_store.auth.application.dto;

import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponseDTO {
    String message;
    User user;
}
