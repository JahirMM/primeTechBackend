package com.primetech.primetech_store.Offer.domain.models;

import com.primetech.primetech_store.product.domain.models.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "offer", schema = "prime_tech_schema")
public class Offer {
    @Id
    @GeneratedValue
    @Column(name = "offer_id", nullable = false)
    private UUID offerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @Column(name = "discount_percentage", nullable = false)
    private BigDecimal discountPercentage;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    public Offer() {this.offerId = UUID.randomUUID();}

    public Offer(Product product, BigDecimal discountPercentage, LocalDateTime startDate, LocalDateTime endDate, boolean isActive) {
        this.product = product;
        this.discountPercentage = discountPercentage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }
}
