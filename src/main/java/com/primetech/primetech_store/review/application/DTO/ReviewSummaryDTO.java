package com.primetech.primetech_store.review.application.DTO;

import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSummaryDTO {
    private User user;
    private UUID reviewId;
    private Double rating;
    private String comment;
    private LocalDateTime createdAt;
}
