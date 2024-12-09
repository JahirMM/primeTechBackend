package com.primetech.primetech_store.config.orderConfig;

import com.primetech.primetech_store.order.application.AddOrderApplication;
import com.primetech.primetech_store.order.domain.interfaces.OrderServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class OrderConfig {
    private final OrderServiceInterface orderService;

    @Bean
    public AddOrderApplication addOrderApplication() {
        return new AddOrderApplication(orderService);
    }
}
