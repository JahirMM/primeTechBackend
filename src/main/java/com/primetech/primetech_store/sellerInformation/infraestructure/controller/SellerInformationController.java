package com.primetech.primetech_store.sellerInformation.infraestructure.controller;

import com.primetech.primetech_store.sellerInformation.application.DTO.SellerInformationDTO;
import com.primetech.primetech_store.sellerInformation.application.GetSellerInformationApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/prime-tech/api/v1/seller-information")
@RequiredArgsConstructor
public class SellerInformationController {
    private final GetSellerInformationApplication getSellerInformation;

    @GetMapping("/{sellerId}")
    public ResponseEntity<SellerInformationDTO> getSellerInformation(@PathVariable UUID sellerId) {
        SellerInformationDTO sellerInformation = getSellerInformation.getSellerInformation(sellerId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(sellerInformation);
    }
}
