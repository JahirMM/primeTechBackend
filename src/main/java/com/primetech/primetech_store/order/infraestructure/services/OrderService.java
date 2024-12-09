package com.primetech.primetech_store.order.infraestructure.services;

import com.primetech.primetech_store.order.domain.interfaces.OrderServiceInterface;
import com.primetech.primetech_store.order.domain.models.Order;
import com.primetech.primetech_store.order.infraestructure.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceInterface {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> findByUserId(UUID userId) {
        return orderRepository.findByUser_UserId(userId);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
