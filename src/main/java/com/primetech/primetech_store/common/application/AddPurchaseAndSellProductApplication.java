package com.primetech.primetech_store.common.application;

import com.primetech.primetech_store.common.application.DTO.AddPurchaseAndSellProductRequestDTO;
import com.primetech.primetech_store.common.application.exception.InsufficientStockException;
import com.primetech.primetech_store.order.application.AddOrderApplication;
import com.primetech.primetech_store.order.domain.models.Order;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class AddPurchaseAndSellProductApplication {
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;
    private final ProductImageServiceInterface productImageService;

    private final AddPurchasedProductApplication addPurchasedProductApplication;
    private final AddSoldProductApplication addSoldProductApplication;
    private final AddOrderApplication orderApplication;

    @Transactional
    public List<PurchasedProductDetailsDTO> addPurchaseAndSellProduct(String email, List<AddPurchaseAndSellProductRequestDTO> request) {

        List<AddPurchaseAndSellProductRequestDTO> uniqueRequests = new ArrayList<>();
        User user = userService.findUserInformationByEmail(email);

        Order order = orderApplication.addOrder(user);

        request.forEach(product -> {
            AddPurchaseAndSellProductRequestDTO searchProduct = findProductInList(uniqueRequests, product.getProductId());
            if (searchProduct != null) {
                searchProduct.setPurchaseQuantity(searchProduct.getPurchaseQuantity() + product.getPurchaseQuantity());
            } else {
                uniqueRequests.add(product);
            }
        });

        List<PurchasedProductDetailsDTO> purchasedProducts  = uniqueRequests.stream().map(
                item -> {
                    Product product = productService.findProductByProductId(item.getProductId());

                    if (product.getStock() < item.getPurchaseQuantity()) {
                        throw new InsufficientStockException("Not enough stock for the product");
                    }

                    product.setStock(product.getStock() - item.getPurchaseQuantity());
                    productService.saveProduct(product);

                    ProductImage productImage = productImageService.findProductImagByProductIdAndMainTrue(product.getProductId());
                    String imgUrl = productImage != null ?  productImage.getImgURL() : null;

                    PurchasedProduct purchasedProduct = addPurchasedProductApplication.addPurchasedProduct(user, order, product, imgUrl, item.getPurchaseQuantity());
                    addSoldProductApplication.addSoldProduct(product, imgUrl, item.getPurchaseQuantity());
                    return new PurchasedProductDetailsDTO(purchasedProduct);
                }
        ).toList();

        return purchasedProducts ;
    }

    private AddPurchaseAndSellProductRequestDTO findProductInList(List<AddPurchaseAndSellProductRequestDTO> requestList, UUID productId) {
        return requestList.stream()
                .filter(
                        product -> product.getProductId().equals(productId)
                )
                .findFirst()
                .orElse(null);
    }
}
