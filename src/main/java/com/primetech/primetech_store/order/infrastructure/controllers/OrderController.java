package com.primetech.primetech_store.order.infrastructure.controllers;

import com.primetech.primetech_store.order.application.DTO.OrderDetailsDTO;
import com.primetech.primetech_store.order.application.GetOrdersApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/prime-tech/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final GetOrdersApplication getOrdersApplication;

    @GetMapping("")
    public ResponseEntity<Map<String, List<OrderDetailsDTO>>> getOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, List<OrderDetailsDTO>> response = new HashMap<>();

        if (authentication != null && authentication.isAuthenticated()) {
            List<OrderDetailsDTO> orders = getOrdersApplication.getOrders(authentication.getName());
            response.put("orders", orders);
            return ResponseEntity.ok(response);
        } else {
            response.put("Please log in", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }
}
