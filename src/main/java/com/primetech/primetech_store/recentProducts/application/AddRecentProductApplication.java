package com.primetech.primetech_store.recentProducts.application;

import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.recentProducts.domain.interfaces.RecentProductServiceInterface;
import com.primetech.primetech_store.recentProducts.domain.models.RecentProduct;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class AddRecentProductApplication {
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;
    private final RecentProductServiceInterface recentProductService;

    @Transactional
    public void addRecentProduct(String email, UUID productId) {
        User user = userService.findUserInformationByEmail(email);
        UUID userId = user.getUserId();

        if (recentProductService.existsByProductIdAndUserId(productId, userId)) {
            RecentProduct existingProduct = recentProductService.findByProductIdAndUserId(productId, userId);
            existingProduct.setVisitData(LocalDateTime.now());
            recentProductService.saveRecentProduct(existingProduct);
            return;
        }

        List<RecentProduct> recentProducts = recentProductService.getRecentProducts(userId);

        if (recentProducts.size() >= 20) {
            RecentProduct oldestProduct = recentProducts.stream()
                    .min(Comparator.comparing(RecentProduct::getVisitData))
                    .orElseThrow();
            recentProductService.deleteRecentProduct(oldestProduct);
        }

        Product product = productService.findProductByProductId(productId);
        RecentProduct newRecentProduct = new RecentProduct(userId, product, LocalDateTime.now());

        recentProductService.saveRecentProduct(newRecentProduct);
    }
}
