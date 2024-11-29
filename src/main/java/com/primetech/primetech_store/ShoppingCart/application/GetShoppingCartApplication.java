package com.primetech.primetech_store.ShoppingCart.application;

import com.primetech.primetech_store.ShoppingCart.application.dto.ShoppingCartDetailsDTO;
import com.primetech.primetech_store.ShoppingCart.application.dto.ShoppingCartProductDetailsDTO;
import com.primetech.primetech_store.ShoppingCart.domain.interfaces.ShoppingCartServiceInterface;
import com.primetech.primetech_store.ShoppingCart.domain.models.ShoppingCart;
import com.primetech.primetech_store.ShoppingCart.domain.models.ShoppingCartItems;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.product.domain.models.ProductImage;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GetShoppingCartApplication {
    private final ShoppingCartServiceInterface shoppingCartService;
    private final UserServiceInterface userService;
    private final ProductImageServiceInterface productImageService;

    public ShoppingCartDetailsDTO getShoppingCart(String email) {
        User user = userService.findUserInformationByEmail(email);
        ShoppingCart shoppingCart = shoppingCartService.findByUserId(user.getUserId());

        List<ShoppingCartItems> shoppingCartItems = shoppingCartService.findItemsByShoppingCartId(shoppingCart.getShoppingCartId());

        List<ShoppingCartProductDetailsDTO> productDetails = shoppingCartItems.stream()
                .map(item -> {
                    Product product = item.getProduct();
                    ProductImage mainImage = productImageService.findProductImagByProductIdAndMainTrue(product.getProductId());
                    String imgUrl = mainImage != null ? mainImage.getImgURL() : null;

                    return new ShoppingCartProductDetailsDTO(
                            product,
                            imgUrl,
                            item.getQuantity()
                    );
                })
                .collect(Collectors.toList());

        return new ShoppingCartDetailsDTO(
                shoppingCart.getShoppingCartId(),
                shoppingCart.getCreatedAt(),
                shoppingCart.isCompleted(),
                productDetails
        );
    }
}

