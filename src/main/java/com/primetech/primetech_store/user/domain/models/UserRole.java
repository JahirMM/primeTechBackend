package com.primetech.primetech_store.user.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "user_role", schema = "prime_tech_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    @Id
    @Column(name = "role_id", nullable = false)
    private UUID roleId;
    @Column(name = "role_name", nullable = false)
    private String roleName;
}
