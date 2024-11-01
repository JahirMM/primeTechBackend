package com.primetech.primetech_store.user.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Table(name = "user_image", schema = "prime_tech_schema")
public class UserImage {

    @Id
    @Column(name = "user_image_id")
    private UUID userImageId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "img_url")
    private String imgURL;

    public UserImage() {
        this.userImageId = UUID.randomUUID();
    }
}
