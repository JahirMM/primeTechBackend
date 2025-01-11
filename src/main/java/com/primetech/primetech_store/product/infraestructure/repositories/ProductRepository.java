package com.primetech.primetech_store.product.infraestructure.repositories;

import com.primetech.primetech_store.product.application.DTO.PriceRangeDTO;
import com.primetech.primetech_store.product.domain.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findProductByProductId(UUID productId);
    boolean existsByProductId(UUID productId);

    @Query("SELECT new com.primetech.primetech_store.product.application.DTO.PriceRangeDTO(MIN(p.price), MAX(p.price)) FROM Product p")
    PriceRangeDTO findPriceRange();

    @Query("SELECT p FROM Product p " +
            "LEFT JOIN Review r ON p.productId = r.product.productId " +
            "LEFT JOIN Offer o ON p.productId = o.product.productId " +
            "WHERE " +
            "(p.stock > 0) AND " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:brand IS NULL OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :brand, '%'))) AND " +
            "(:categoryId IS NULL OR p.category.categoryId = :categoryId) AND " +
            "(:sellerId IS NULL OR p.user.userId = :sellerId) AND " +
            "(p.price BETWEEN :minPrice AND :maxPrice) AND " +
            "(:onSale IS NULL OR (:onSale = TRUE AND o.isActive = TRUE)) " +
            "GROUP BY p.productId, p.name, p.brand, p.category, p.user, p.price, p.stock " +
            "HAVING (:minRating IS NULL OR COALESCE(AVG(r.rating), 0) >= :minRating)")
    Page<Product> findByCriteria(@Param("name") String name,
                                 @Param("brand") String brand,
                                 @Param("categoryId") UUID categoryId,
                                 @Param("sellerId") UUID sellerId,
                                 @Param("minPrice") BigDecimal minPrice,
                                 @Param("maxPrice") BigDecimal maxPrice,
                                 @Param("minRating") Double minRating,
                                 @Param("onSale") Boolean onSale,
                                 Pageable pageable);

    Optional<Product> findByProductIdAndUser_UserId(UUID productId, UUID userId);
    void deleteByProductId(UUID productId);
}
