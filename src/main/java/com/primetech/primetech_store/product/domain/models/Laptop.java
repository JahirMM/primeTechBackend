package com.primetech.primetech_store.product.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "laptop", schema = "prime_tech_schema")
public class Laptop {
    @Id
    @Column(name = "laptop_id", nullable = false)
    private final UUID laptopId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id", referencedColumnName = "device_id", nullable = false)
    private Device device;

    @Column(name = "ram", nullable = false)
    private int ram;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "processor", nullable = false)
    private String processor;

    @Column(name = "touchscreen", nullable = false)
    private boolean touchscreen;

    @Column(name = "operating_system", nullable = false)
    private String operatingSystem;

    @Column(name = "keyboard_language", nullable = false)
    private String keyboardLanguage;

    @Column(name = "backlit_keyboard", nullable = false)
    private boolean backlitKeyboard;

    @Column(name = "graphic_card", nullable = false)
    private String graphicCard;

    @Column(name = "usb_ports", nullable = false)
    private int usbPorts;

    @Column(name = "usb_c_ports", nullable = false)
    private int usbCPorts;

    @Column(name = "hdmi_ports", nullable = false)
    private int hdmiPorts;

    @Column(name = "with_wifi", nullable = false)
    private boolean withWifi;

    @Column(name = "with_bluetooth", nullable = false)
    private boolean withBluetooth;

    @Column(name = "with_ethernet_port", nullable = false)
    private boolean withEthernetPort;

    // ejemplo 1TB, 500GB, 0GB, etc
    @Column(name = "ssd_storage", nullable = false)
    private int ssdStorage;

    // ejemplo 1TB, 500GB, 0GB, etc
    @Column(name = "hdd_storage", nullable = false)
    private int hddStorage;

    @Column(name = "quantity_speakers", nullable = false)
    private int quantitySpeakers;

    @Column(name = "microphone", nullable = false)
    private int microphone;

    public Laptop() {
        this.laptopId = UUID.randomUUID();
    }

    public Laptop(Device device, int ram, String color, String processor, boolean touchscreen, String operatingSystem,
                  String keyboardLanguage, boolean backlitKeyboard, String graphicCard, int usbPorts, int usbCPorts,
                  int hdmiPorts, boolean withWifi, boolean withBluetooth, boolean withEthernetPort,
                  int ssdStorage, int hddStorage, int quantitySpeakers, int microphone) {
        this();
        this.device = device;
        this.ram = ram;
        this.color = color;
        this.processor = processor;
        this.touchscreen = touchscreen;
        this.operatingSystem = operatingSystem;
        this.keyboardLanguage = keyboardLanguage;
        this.backlitKeyboard = backlitKeyboard;
        this.graphicCard = graphicCard;
        this.usbPorts = usbPorts;
        this.usbCPorts = usbCPorts;
        this.hdmiPorts = hdmiPorts;
        this.withWifi = withWifi;
        this.withBluetooth = withBluetooth;
        this.withEthernetPort = withEthernetPort;
        this.ssdStorage = ssdStorage;
        this.hddStorage = hddStorage;
        this.quantitySpeakers = quantitySpeakers;
        this.microphone = microphone;
    }
}
