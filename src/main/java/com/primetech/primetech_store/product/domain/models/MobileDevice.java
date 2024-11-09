package com.primetech.primetech_store.product.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "mobile_device", schema = "prime_tech_schema")
public class MobileDevice {
     @Id
     @Column(name = "mobile_device_id")
    private UUID mobileDeviceId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id", referencedColumnName = "device_id")
    private Device device;

    @Column(name = "internal_memory")
    private int internalMemory;

    @Column(name = "internal_memory_type")
    private String internalMemoryType;

    @Column(name = "ram")
    private int ram;

    @Column(name = "color")
    private String color;

    @Column(name = "processor")
    private String processor;

    @Column(name = "operating_system")
    private String operatingSystem;

    @Column(name = "ip_rating")
    private String ipRating;

    @Column(name = "splash_resistant")
    private boolean splashResistant;

    @Column(name = "dust_resistant")
    private boolean dustResistant;

    @Column(name = "water_resistant")
    private boolean waterResistant;

    public MobileDevice() {
        this.mobileDeviceId = UUID.randomUUID();
    }

    public MobileDevice(Device device, int internalMemory, String internalMemoryType, int ram, String color,
                        String processor, String operatingSystem, String ipRating, boolean splashResistant,
                        boolean dustResistant, boolean waterResistant) {
        this();
        this.device = device;
        this.internalMemory = internalMemory;
        this.internalMemoryType = internalMemoryType;
        this.ram = ram;
        this.color = color;
        this.processor = processor;
        this.operatingSystem = operatingSystem;
        this.ipRating = ipRating;
        this.splashResistant = splashResistant;
        this.dustResistant = dustResistant;
        this.waterResistant = waterResistant;
    }
}
