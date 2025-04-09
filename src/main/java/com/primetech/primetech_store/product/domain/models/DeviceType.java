package com.primetech.primetech_store.product.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "device_type", schema = "prime_tech_schema")
public class DeviceType {
    @Id
    @Column(name = "device_type_id", nullable = false)
    private UUID deviceTypeId;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    public DeviceType() {
        this.deviceTypeId = UUID.randomUUID();
    }

    public DeviceType(String typeName) {
        this();
        this.typeName = typeName;
    }

    public DeviceType(UUID deviceTypeId, String typeName) {
        this.deviceTypeId = deviceTypeId;
        this.typeName = typeName;
    }
}
