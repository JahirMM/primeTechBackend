package com.primetech.primetech_store.product.application.DTO.battery;

import com.primetech.primetech_store.product.domain.models.Battery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatteryDTO {
    private UUID batteryId;
    private String capacity;
    private String type;
    private boolean wirelessCharging;
    private boolean fastCharging;
    private BigDecimal maxBatteryDuration;

    public static BatteryDTO from(Battery battery) {
        return new BatteryDTO(
                battery.getBatteryId(),
                battery.getCapacity(),
                battery.getType(),
                battery.isWirelessCharging(),
                battery.isFastCharging(),
                battery.getMaxBatteryDuration()
        );
    }
}
