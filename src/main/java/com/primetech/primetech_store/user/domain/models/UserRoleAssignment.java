package com.primetech.primetech_store.user.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "user_role_assignment", schema = "prime_tech_schema")
@Data
@AllArgsConstructor
public class UserRoleAssignment {
    @Id
    @Column(name = "user_role_assignment_id", nullable = false)
    private UUID userRoleAssigmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    private UserRole userRole;

    public UserRoleAssignment() {
        this.userRoleAssigmentId = UUID.randomUUID();
    }
}
