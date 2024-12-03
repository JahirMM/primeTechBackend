package com.primetech.primetech_store.purchasedProduct.application;

import com.primetech.primetech_store.common.exception.InsufficientStockException;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.product.domain.models.ProductImage;
import com.primetech.primetech_store.purchasedProduct.domain.interfaces.PurchasedProductServiceInterface;
import com.primetech.primetech_store.purchasedProduct.domain.models.PurchasedProduct;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class AddPurchasedProductApplication {
    private final PurchasedProductServiceInterface purchasedProductService;
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;
    private final ProductImageServiceInterface productImageService;

    @Transactional
    public PurchasedProduct AddPurchasedProduct(String email, int quantity, UUID productId) {
        User user = userService.findUserInformationByEmail(email);
        Product product = productService.findProductByProductId(productId);

        if (product.getStock() < quantity) {
            throw new InsufficientStockException("Not enough stock for the product");
        }

        product.setStock(product.getStock() - quantity);
        productService.saveProduct(product);

        ProductImage productImage = productImageService.findProductImagByProductIdAndMainTrue(product.getProductId());
        String imgUrl = productImage != null ?  productImage.getImgURL() : null;

        PurchasedProduct purchasedProduct = new PurchasedProduct(user, product.getProductId(), product.getName(), product.getDescription(), product.getPrice(), imgUrl, quantity);

        return purchasedProductService.savePurchasedProduct(purchasedProduct);
    }
}
