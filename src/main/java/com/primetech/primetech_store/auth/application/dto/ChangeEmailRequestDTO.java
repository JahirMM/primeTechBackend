package com.primetech.primetech_store.auth.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeEmailRequestDTO {
    private String email;
    private String password;
}
