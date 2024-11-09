package com.primetech.primetech_store.product.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "battery", schema = "prime_tech_schema")
public class Battery {
    @Id
    @Column(name = "battery_id", nullable = false)
    private UUID batteryId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id", referencedColumnName = "device_id", nullable = false)
    private Device device;

    @Column(name = "capacity", nullable = false)
    private String capacity;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "wireless_charging", nullable = false)
    private boolean wirelessCharging;

    @Column(name = "fast_charging", nullable = false)
    private boolean fastCharging;

    @Column(name = " max_battery_duration", nullable = false)
    private BigDecimal  maxBatteryDuration;

    public Battery() {
        this.batteryId = UUID.randomUUID();
    }

    public Battery(Device device, String capacity, String type,
                  boolean wirelessCharging, boolean fastCharging, BigDecimal maxBatteryDuration) {
        this();
        this.device = device;
        this.capacity  = capacity;
        this.type = type;
        this.wirelessCharging = wirelessCharging;
        this.fastCharging = fastCharging;
        this.maxBatteryDuration = maxBatteryDuration;
    }
}
