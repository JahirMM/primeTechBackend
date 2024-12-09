package com.primetech.primetech_store.order.infraestructure.services;

import com.primetech.primetech_store.order.domain.interfaces.OrderServiceInterface;
import com.primetech.primetech_store.order.domain.models.Order;
import com.primetech.primetech_store.order.infraestructure.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceInterface {
    private final OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
