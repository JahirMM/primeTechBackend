package com.primetech.primetech_store.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserImageResponseDTO {
    private String message;
    private UserImageDTO userImage;
}
