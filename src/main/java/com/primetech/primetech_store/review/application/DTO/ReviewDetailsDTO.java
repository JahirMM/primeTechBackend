package com.primetech.primetech_store.review.application.DTO;

import com.primetech.primetech_store.user.domain.models.User;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReviewDetailsDTO {
    private String name;
    private UUID reviewId;
    private Double rating;
    private String comment;
    private LocalDateTime createdAt;

    public ReviewDetailsDTO(User user, UUID reviewId,Double rating, String comment, LocalDateTime createdAt) {
        this.name = user.getFirstName() + " " + user.getPaternalSurname() + " " + user.getMaternalSurname();
        this.reviewId = reviewId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }
}
