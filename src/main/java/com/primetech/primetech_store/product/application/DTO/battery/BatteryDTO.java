package com.primetech.primetech_store.product.application.DTO.battery;

import com.primetech.primetech_store.product.domain.models.Battery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatteryDTO {
    private String capacity;
    private String type;
    private boolean wirelessCharging;
    private boolean fastCharging;
    private BigDecimal maxBatteryDuration;

    public static BatteryDTO from(Battery battery) {
        return new BatteryDTO(
                battery.getCapacity(),
                battery.getType(),
                battery.isWirelessCharging(),
                battery.isFastCharging(),
                battery.getMaxBatteryDuration()
        );
    }
}
