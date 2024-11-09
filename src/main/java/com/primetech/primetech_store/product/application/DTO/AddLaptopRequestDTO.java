package com.primetech.primetech_store.product.application.DTO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.primetech.primetech_store.common.StringDeserializer;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddLaptopRequestDTO {
    @NotNull(message = "RAM is mandatory")
    @Min(value = 1, message = "RAM must be at least 1 GB")
    @Max(value = 128, message = "RAM can have a maximum of 128 GB")
    private int ram;

    @NotBlank(message = "Color is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 20, message = "Color can have a maximum of 20 characters")
    private String color;

    @NotBlank(message = "Processor is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 30, message = "Processor can have a maximum of 30 characters")
    private String processor;

    @NotNull(message = "Touchscreen is mandatory")
    private Boolean touchscreen;

    @NotBlank(message = "Operating system is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 20, message = "Operating system can have a maximum of 20 characters")
    private String operatingSystem;

    @NotBlank(message = "Keyboard language is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 10, message = "Keyboard language can have a maximum of 10 characters")
    private String keyboardLanguage;

    @NotNull(message = "Backlit keyboard is mandatory")
    private Boolean backlitKeyboard;

    @NotBlank(message = "Graphic card is mandatory")
    @JsonDeserialize(using = StringDeserializer.class)
    @Size(max = 30, message = "Graphic card can have a maximum of 30 characters")
    private String graphicCard;

    @NotNull(message = "USB ports count is mandatory")
    @Min(value = 0, message = "USB ports must be 0 or higher")
    private int usbPorts;

    @NotNull(message = "USB-C ports count is mandatory")
    @Min(value = 0, message = "USB-C ports must be 0 or higher")
    private int usbCPorts;

    @NotNull(message = "HDMI ports count is mandatory")
    @Min(value = 0, message = "HDMI ports must be 0 or higher")
    private int hdmiPorts;

    @NotNull(message = "Wi-Fi availability is mandatory")
    private Boolean withWifi;

    @NotNull(message = "Bluetooth availability is mandatory")
    private Boolean withBluetooth;

    @NotNull(message = "Ethernet port availability is mandatory")
    private Boolean withEthernetPort;

    @NotNull(message = "SSD storage is mandatory")
    @Min(value = 0, message = "SSD storage must be 0 or higher")
    private int ssdStorage;

    @NotNull(message = "HDD storage is mandatory")
    @Min(value = 0, message = "HDD storage must be 0 or higher")
    private int hddStorage;

    @NotNull(message = "Speaker quantity is mandatory")
    @Min(value = 1, message = "There must be at least 1 speaker")
    private int quantitySpeakers;

    @NotNull(message = "Microphone quantity is mandatory")
    @Min(value = 0, message = "Microphone quantity must be 0 or higher")
    private int microphone;
}
