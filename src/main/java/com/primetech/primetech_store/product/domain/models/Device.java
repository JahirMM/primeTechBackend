package com.primetech.primetech_store.product.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "device", schema = "prime_tech_schema")
public class Device {
    @Id
    @Column(name = "device_id", nullable = false)
    private final UUID deviceId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_type_id", referencedColumnName = "device_type_id", nullable = false)
    private DeviceType deviceType;

    public Device() {
        this.deviceId = UUID.randomUUID();
    }

    public Device(Product product, DeviceType deviceType) {
        this();
        this.product = product;
        this.deviceType = deviceType;
    }
}
