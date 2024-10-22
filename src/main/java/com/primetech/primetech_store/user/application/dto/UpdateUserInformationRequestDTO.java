package com.primetech.primetech_store.user.application.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserInformationRequestDTO {
    @Email(message = "Email should be valid")
    private String email;
    private String firstName;
    private String middleName;
    private String paternalSurname;
    private String maternalSurname;
}
