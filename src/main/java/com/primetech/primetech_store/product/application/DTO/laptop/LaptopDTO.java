package com.primetech.primetech_store.product.application.DTO.laptop;

import com.primetech.primetech_store.product.domain.models.Laptop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaptopDTO {
    private UUID laptopId;
    private int ram;
    private String color;
    private String processor;
    private boolean touchscreen;
    private String operatingSystem;
    private String keyboardLanguage;
    private boolean backlitKeyboard;
    private String graphicCard;
    private int usbPorts;
    private int usbCPorts;
    private int hdmiPorts;
    private boolean withWifi;
    private boolean withBluetooth;
    private boolean withEthernetPort;
    private int ssdStorage;
    private int hddStorage;
    private int quantitySpeakers;
    private int microphone;

    public static LaptopDTO from(Laptop laptop) {
        return new LaptopDTO(
                laptop.getLaptopId(),
                laptop.getRam(),
                laptop.getColor(),
                laptop.getProcessor(),
                laptop.isTouchscreen(),
                laptop.getOperatingSystem(),
                laptop.getKeyboardLanguage(),
                laptop.isBacklitKeyboard(),
                laptop.getGraphicCard(),
                laptop.getUsbPorts(),
                laptop.getUsbCPorts(),
                laptop.getHdmiPorts(),
                laptop.isWithWifi(),
                laptop.isWithBluetooth(),
                laptop.isWithEthernetPort(),
                laptop.getSsdStorage(),
                laptop.getHddStorage(),
                laptop.getQuantitySpeakers(),
                laptop.getMicrophone()
        );
    }
}
