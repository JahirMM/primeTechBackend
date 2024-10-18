package com.primetech.primetech_store.user.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Table(name = "user", schema = "prime_tech_schema")
public class User{
    @Id
    @Column(name = "user_id")
    private UUID userId;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "First name is mandatory")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @NotBlank(message = "Paternal surname is mandatory")
    @Column(name = "paternal_surname")
    private String paternalSurname;

    @NotBlank(message = "Maternal surname is mandatory")
    @Column(name = "maternal_surname")
    private String maternalSurname;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public User() {
        this.userId = UUID.randomUUID();
    }

    public User(String email, String password, String firstName, String middleName, String paternalSurname, String maternalSurname) {
        this();
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
