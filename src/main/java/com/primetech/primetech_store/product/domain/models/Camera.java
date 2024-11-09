package com.primetech.primetech_store.product.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "camera", schema = "prime_tech_schema")
public class Camera {
    @Id
    @Column(name = "camera_id", nullable = false)
    private UUID cameraId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", referencedColumnName = "device_id", nullable = false)
    private Device device;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "resolution", nullable = false)
    private String resolution;

    @Column(name = "aperture", nullable = false)
    private String aperture;

    @Column(name = "optical_zoom", nullable = false)
    private String opticalZoom;

    @Column(name = "digital_zoom", nullable = false)
    private String digitalZoom;

    @Column(name = "feature")
    private String feature;

    public Camera() {
        this.cameraId = UUID.randomUUID();
    }

    public Camera(Device device, String type, String resolution,
                  String aperture, String opticalZoom, String digitalZoom,
                  String feature) {
        this();
        this.device = device;
        this.type = type;
        this.resolution = resolution;
        this.aperture = aperture;
        this.opticalZoom = opticalZoom;
        this.digitalZoom = digitalZoom;
        this.feature = feature;
    }
}
