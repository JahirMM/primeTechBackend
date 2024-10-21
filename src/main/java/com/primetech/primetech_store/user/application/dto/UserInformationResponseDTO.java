package com.primetech.primetech_store.user.application.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserInformationResponseDTO {
    private UserDTO user;
    private String message;
}
