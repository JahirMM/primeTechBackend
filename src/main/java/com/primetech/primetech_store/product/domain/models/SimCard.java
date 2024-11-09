package com.primetech.primetech_store.product.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "sim_card", schema = "prime_tech_schema")
public class SimCard {
    @Id
    @Column(name = "sim_card_id", nullable = false)
    private UUID simCardId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mobile_device_id", referencedColumnName = "mobile_device_id", nullable = false)
    private MobileDevice mobileDevice;

    @Column(name = "is_dual_sim", nullable = false)
    private boolean isDualSim;

    @Column(name = "sim_slots", nullable = false)
    private Integer simSlots;

    @Column(name = "esim", nullable = false)
    private boolean esim;

    @Column(name = "sim_type", nullable = false)
    private String simType;

    public SimCard() {
        this.simCardId = UUID.randomUUID();
    }

    public SimCard(MobileDevice mobileDevice, boolean isDualSim, Integer simSlots, boolean esim, String simType) {
        this();
        this.mobileDevice = mobileDevice;
        this.isDualSim = isDualSim;
        this.simSlots = simSlots;
        this.esim = esim;
        this.simType = simType;
    }
}
