package com.primetech.primetech_store.product.application.DTO.screen;
import com.primetech.primetech_store.product.domain.models.Screen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScreenDTO {
    private String resolution;
    private String pixelDensity;
    private String refreshRate;
    private String screenType;
    private BigDecimal screenSize;

    public static ScreenDTO from(Screen screen) {
        return new ScreenDTO(
                screen.getResolution(),
                screen.getPixelDensity(),
                screen.getRefreshRate(),
                screen.getScreenType(),
                screen.getScreenSize()
        );
    }
}
