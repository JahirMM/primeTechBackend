package com.primetech.primetech_store.product.domain.interfaces;

import com.primetech.primetech_store.product.domain.models.Screen;

import java.util.List;
import java.util.UUID;

public interface ScreenServiceInterface {
    Screen saveScreen(Screen screen);
    boolean screenExistsForDevice(UUID deviceId);
    List<Screen> findScreenInformationByDeviceId(UUID deviceId);
}
