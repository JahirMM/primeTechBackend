package com.primetech.primetech_store.order.application;

import com.primetech.primetech_store.order.domain.interfaces.OrderServiceInterface;
import com.primetech.primetech_store.order.domain.models.Order;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class AddOrderApplication {
    private final OrderServiceInterface orderService;

    @Transactional
    public Order addOrder(User user) {
        return orderService.saveOrder(new Order(user));
    }
}
