package com.primetech.primetech_store.shoppingCart.application;

import com.primetech.primetech_store.common.application.exception.InsufficientStockException;
import com.primetech.primetech_store.shoppingCart.application.dto.ProductToShoppingCartAdderDTO;
import com.primetech.primetech_store.shoppingCart.application.dto.ShoppingCartProductDetailsDTO;
import com.primetech.primetech_store.shoppingCart.domain.interfaces.ShoppingCartServiceInterface;
import com.primetech.primetech_store.shoppingCart.domain.models.ShoppingCart;
import com.primetech.primetech_store.shoppingCart.domain.models.ShoppingCartItems;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.product.domain.models.ProductImage;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class AddProductToShoppingCartApplication {
    private final ShoppingCartServiceInterface shoppingCartService;
    private final ProductServiceInterface productService;
    private final ProductImageServiceInterface productImageService;

    @Transactional
    public ProductToShoppingCartAdderDTO addProductToShoppingCart(String email, UUID productId, int quantityToAdd) {
        Product product = productService.findProductByProductId(productId);
        ProductImage productImage = productImageService.findProductImagByProductIdAndMainTrue(product.getProductId());
        String mainProductImage = productImage != null ? productImage.getImgURL() : null;

        ShoppingCart userShoppingCart = shoppingCartService.getOrCreateShoppingCart(email);
        ShoppingCartItems existingItem = shoppingCartService.findItemInShoppingCart(userShoppingCart.getShoppingCartId(), productId);

        int currentQuantity = existingItem != null ? existingItem.getQuantity() : 0;
        int newQuantity = currentQuantity + quantityToAdd;

        // Validar si la cantidad solicitada supera el stock disponible
        if (quantityToAdd > product.getStock()) {
            throw new InsufficientStockException("You cannot add more than the quantity available in stock.");
        }

        // Validar si la suma de la cantidad actual y la nueva cantidad supera el stock
        if (newQuantity > product.getStock()) {
            throw new InsufficientStockException("You cannot add more than the quantity available in stock..");
        }

        ShoppingCartItems savedItem;
        if (existingItem != null) {
            existingItem.setQuantity(newQuantity);
            savedItem = shoppingCartService.saveItemInShoppingCart(existingItem);
        } else {
            savedItem = shoppingCartService.saveItemInShoppingCart(new ShoppingCartItems(userShoppingCart, product, quantityToAdd));
        }

        return new ProductToShoppingCartAdderDTO(
                userShoppingCart.getShoppingCartId(),
                userShoppingCart.getCreatedAt(),
                userShoppingCart.isCompleted(),
                new ShoppingCartProductDetailsDTO(savedItem.getProduct(), mainProductImage, savedItem.getQuantity())
        );
    }
}


