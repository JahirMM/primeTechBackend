package com.primetech.primetech_store.order.domain.interfaces;

import com.primetech.primetech_store.order.domain.models.Order;

public interface OrderServiceInterface {
    Order saveOrder(Order order);
}
