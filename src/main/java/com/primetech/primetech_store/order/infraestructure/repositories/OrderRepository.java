package com.primetech.primetech_store.order.infraestructure.repositories;

import com.primetech.primetech_store.order.domain.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUser_UserId(UUID userId);
}
