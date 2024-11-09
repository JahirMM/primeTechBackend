package com.primetech.primetech_store.product.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "screen", schema = "prime_tech_schema")
public class Screen {
    @Id
    @Column(name = "screen_id", nullable = false)
    private UUID screenId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id", referencedColumnName = "device_id", nullable = false)
    private Device device;

    @Column(name = "resolution", nullable = false)
    private String resolution;

    @Column(name = "pixel_density", nullable = false)
    private String pixelDensity;

    @Column(name = "refresh_rate", nullable = false)
    private String refreshRate;

    @Column(name = "screen_type", nullable = false)
    private String screenType;

    @Column(name = "screen_size", nullable = false)
    private BigDecimal screenSize;

    public Screen() {
        this.screenId = UUID.randomUUID();
    }

    public Screen(Device device, String resolution, String pixelDensity,
                  String refreshRate, String screenType, BigDecimal screenSize) {
        this();
        this.device = device;
        this.resolution  = resolution;
        this.pixelDensity = pixelDensity;
        this.refreshRate = refreshRate;
        this.screenType = screenType;
        this.screenSize = screenSize;
    }
}
