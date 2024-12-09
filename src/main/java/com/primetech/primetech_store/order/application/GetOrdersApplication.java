package com.primetech.primetech_store.order.application;

import com.primetech.primetech_store.order.application.DTO.OrderDetailsDTO;
import com.primetech.primetech_store.order.domain.interfaces.OrderServiceInterface;
import com.primetech.primetech_store.order.domain.models.Order;
import com.primetech.primetech_store.purchasedProduct.domain.interfaces.PurchasedProductServiceInterface;
import com.primetech.primetech_store.purchasedProduct.domain.models.PurchasedProduct;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class GetOrdersApplication {
    private final OrderServiceInterface orderService;
    private final UserServiceInterface userService;
    private final PurchasedProductServiceInterface purchasedProductService;

    @Transactional
    public List<OrderDetailsDTO> getOrders(String email) {
        User user = userService.findUserInformationByEmail(email);

        List<Order> orders = orderService.findByUserId(user.getUserId());

        return orders.stream()
                .map(order -> {
                    List<PurchasedProduct> purchasedProducts = purchasedProductService.findByOrderIdAndUserId(order.getOrderId(), user.getUserId());
                    return new OrderDetailsDTO(order, purchasedProducts);
                })
                .toList();
    }
}
