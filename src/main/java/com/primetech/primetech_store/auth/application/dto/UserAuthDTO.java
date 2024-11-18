package com.primetech.primetech_store.auth.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDTO {
    private String email;
    private String firstName;
    private String middleName;
    private String paternalSurname;
    private String maternalSurname;
    private LocalDateTime createdAt;
}
