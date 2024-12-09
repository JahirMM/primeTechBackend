package com.primetech.primetech_store.order.domain.interfaces;

import com.primetech.primetech_store.order.domain.models.Order;

import java.util.List;
import java.util.UUID;

public interface OrderServiceInterface {
    List<Order> findByUserId(UUID userId);
    Order saveOrder(Order order);
}
