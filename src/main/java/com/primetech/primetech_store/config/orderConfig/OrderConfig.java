package com.primetech.primetech_store.config.orderConfig;

import com.primetech.primetech_store.order.application.AddOrderApplication;
import com.primetech.primetech_store.order.application.GetOrdersApplication;
import com.primetech.primetech_store.order.domain.interfaces.OrderServiceInterface;
import com.primetech.primetech_store.purchasedProduct.domain.interfaces.PurchasedProductServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class OrderConfig {
    private final OrderServiceInterface orderService;
    private final UserServiceInterface userService;
    private final PurchasedProductServiceInterface PurchasedProductService;

    @Bean
    public AddOrderApplication addOrderApplication() {
        return new AddOrderApplication(orderService);
    }

    @Bean
    public GetOrdersApplication getOrdersApplication() {
        return new GetOrdersApplication(
                orderService, userService,
                PurchasedProductService
        );
    }
}
