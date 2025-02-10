package com.primetech.primetech_store.shoppingCart.application;

import com.primetech.primetech_store.common.application.exception.InsufficientStockException;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.shoppingCart.application.dto.ShoppingCartItemUpdaterDTO;
import com.primetech.primetech_store.shoppingCart.domain.interfaces.ShoppingCartServiceInterface;
import com.primetech.primetech_store.shoppingCart.domain.models.ShoppingCartItems;
import com.primetech.primetech_store.common.application.exception.ProductNotFoundException;
import com.primetech.primetech_store.common.application.exception.QuantityMustBeGreaterThanZeroException;
import com.primetech.primetech_store.common.application.exception.ShoppingCartNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class UpdateShoppingCartItemApplication {
    private final ShoppingCartServiceInterface shoppingCartService;
    private final ProductServiceInterface productService;

    @Transactional
    public ShoppingCartItemUpdaterDTO updateShoppingCartItem(UUID shoppingCartId, UUID productId, int quantity) {
        if (!shoppingCartService.exitsByShoppingCartId(shoppingCartId)) {
            throw new ShoppingCartNotFoundException("Shopping cart not found");
        }

        Product product = productService.findProductByProductId(productId);

        if (quantity > product.getStock()) {
            throw new InsufficientStockException("You cannot add more than the quantity available in stock.");
        }

        if (quantity <= 0) {
            throw new QuantityMustBeGreaterThanZeroException("Quantity must be greater than 0");
        }

        ShoppingCartItems existingItem = shoppingCartService.findItemInShoppingCart(shoppingCartId, productId);

        if (existingItem == null) {
            throw new ProductNotFoundException("Product not found in the shopping cart");
        }

        existingItem.setQuantity(quantity);
        ShoppingCartItems updatedItem = shoppingCartService.saveItemInShoppingCart(existingItem);

        return new ShoppingCartItemUpdaterDTO(
                updatedItem.getShoppingCart().getShoppingCartId(),
                updatedItem.getProduct().getProductId(),
                updatedItem.getQuantity()
        );
    }
}
