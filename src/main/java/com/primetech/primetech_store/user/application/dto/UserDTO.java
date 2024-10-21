package com.primetech.primetech_store.user.application.dto;

import com.primetech.primetech_store.user.domain.models.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTO {
    private String email;
    private String firstName;
    private String middleName;
    private String paternalSurname;
    private String maternalSurname;
    private LocalDateTime createdAt;
    private List<String> roleNames;

    public UserDTO(User user, List<String> rolesNames) {
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.middleName = user.getMiddleName();
        this.paternalSurname = user.getPaternalSurname();
        this.maternalSurname = user.getMaternalSurname();
        this.createdAt = user.getCreatedAt();
        this.roleNames = rolesNames;
    }
}
