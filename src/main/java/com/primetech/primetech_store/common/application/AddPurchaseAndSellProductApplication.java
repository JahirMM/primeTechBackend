package com.primetech.primetech_store.common.application;

import com.primetech.primetech_store.common.exception.InsufficientStockException;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.product.domain.models.ProductImage;
import com.primetech.primetech_store.purchasedProduct.application.AddPurchasedProductApplication;
import com.primetech.primetech_store.purchasedProduct.application.DTO.PurchasedProductDetailsDTO;
import com.primetech.primetech_store.purchasedProduct.domain.models.PurchasedProduct;
import com.primetech.primetech_store.soldProduct.application.AddSoldProductApplication;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class AddPurchaseAndSellProductApplication {
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;
    private final ProductImageServiceInterface productImageService;

    private final AddPurchasedProductApplication addPurchasedProductApplication;
    private final AddSoldProductApplication addSoldProductApplication;


    @Transactional
    public PurchasedProductDetailsDTO addPurchaseAndSellProduct(String email, int quantity, UUID productId) {
        User user = userService.findUserInformationByEmail(email);
        Product product = productService.findProductByProductId(productId);

        if (product.getStock() < quantity) {
            throw new InsufficientStockException("Not enough stock for the product");
        }

        product.setStock(product.getStock() - quantity);
        productService.saveProduct(product);

        ProductImage productImage = productImageService.findProductImagByProductIdAndMainTrue(product.getProductId());
        String imgUrl = productImage != null ?  productImage.getImgURL() : null;

        PurchasedProduct purchasedProduct = addPurchasedProductApplication.addPurchasedProduct(user, product, imgUrl, quantity);
        addSoldProductApplication.addSoldProduct(product, imgUrl, quantity);

        return new PurchasedProductDetailsDTO(purchasedProduct);
    }
}
