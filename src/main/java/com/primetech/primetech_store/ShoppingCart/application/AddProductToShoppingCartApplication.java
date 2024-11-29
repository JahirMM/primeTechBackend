package com.primetech.primetech_store.ShoppingCart.application;

import com.primetech.primetech_store.ShoppingCart.application.dto.ProductToShoppingCartAdderDTO;
import com.primetech.primetech_store.ShoppingCart.application.dto.ShoppingCartProductDetailsDTO;
import com.primetech.primetech_store.ShoppingCart.domain.interfaces.ShoppingCartServiceInterface;
import com.primetech.primetech_store.ShoppingCart.domain.models.ShoppingCart;
import com.primetech.primetech_store.ShoppingCart.domain.models.ShoppingCartItems;
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
    public ProductToShoppingCartAdderDTO addProductToShoppingCart(String email, UUID productId) {
        Product product = productService.findProductByProductId(productId);
        ProductImage productImage = productImageService.findProductImagByProductIdAndMainTrue(product.getProductId());
        String mainProductImage = productImage != null ? productImage.getImgURL() : null;
        int quantity = 0;

        ShoppingCart userShoppingCart = shoppingCartService.getOrCreateShoppingCart(email);

        ShoppingCartItems existingItem = shoppingCartService.findItemInShoppingCart(userShoppingCart.getShoppingCartId(), productId);
        ShoppingCartItems savedItem;

        if (existingItem != null) {
            quantity = existingItem.getQuantity() + 1;
            existingItem.setQuantity(quantity);
            savedItem = shoppingCartService.saveItemInShoppingCart(existingItem);
        } else {
            quantity = 1;
            savedItem = shoppingCartService.saveItemInShoppingCart(new ShoppingCartItems(userShoppingCart, product, quantity));
        }

        return createResponse(savedItem, mainProductImage, userShoppingCart, quantity);
    }

    private ProductToShoppingCartAdderDTO createResponse(
            ShoppingCartItems cartItem, String productImageUrl,
            ShoppingCart shoppingCart, int quantity
    ) {
        ShoppingCartProductDetailsDTO productDetailsDTO = new ShoppingCartProductDetailsDTO(cartItem.getProduct(), productImageUrl, quantity);
        return new ProductToShoppingCartAdderDTO(
                shoppingCart.getShoppingCartId(),
                shoppingCart.getCreatedAt(),
                shoppingCart.isCompleted(),
                productDetailsDTO
        );
    }
}

