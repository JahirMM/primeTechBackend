package com.primetech.primetech_store.order.domain.models;

import com.primetech.primetech_store.user.domain.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "prime_tech_schema")
@Data
public class Order {
    @Id
    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "status", nullable = false)
    private String status;

    public Order() {
        this.orderId = UUID.randomUUID();
    }

    public Order(User user) {
        this();
        this.user = user;
        this.orderDate = LocalDateTime.now();
        this.status = "PENDING";
    }
}
