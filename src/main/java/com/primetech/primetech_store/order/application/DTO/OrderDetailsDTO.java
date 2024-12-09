package com.primetech.primetech_store.order.application.DTO;

import com.primetech.primetech_store.order.domain.models.Order;
import com.primetech.primetech_store.purchasedProduct.application.DTO.PurchasedProductDetailsDTO;
import com.primetech.primetech_store.purchasedProduct.domain.models.PurchasedProduct;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDetailsDTO {
    private UUID orderId;
    private LocalDateTime orderDate;
    private String status;
    private List<PurchasedProductDetailsDTO> products;

    public OrderDetailsDTO(Order order, List<PurchasedProduct> purchasedProducts) {
        this.orderId = order.getOrderId();
        this.orderDate = order.getOrderDate();
        this.status = order.getStatus();
        this.products = purchasedProducts.stream()
                .map(PurchasedProductDetailsDTO::new)
                .collect(Collectors.toList());
    }
}
