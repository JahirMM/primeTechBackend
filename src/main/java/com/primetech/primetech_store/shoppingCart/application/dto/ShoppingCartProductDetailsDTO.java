package com.primetech.primetech_store.shoppingCart.application.dto;

import com.primetech.primetech_store.product.domain.models.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ShoppingCartProductDetailsDTO {
    private UUID productId;
    private String imgUrl;
    private String name;
    private String brand;
    private int stock;
    private int quantity;
    private BigDecimal price;

    public ShoppingCartProductDetailsDTO(Product product, String imgUrl, int quantity) {
        this.productId = product.getProductId();
        this.imgUrl = imgUrl;
        this.name = product.getName();
        this.brand = product.getBrand();
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.quantity = quantity;
    }
}
