package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.UnauthorizedException;

public class ProductNotPurchasedException extends UnauthorizedException {
    public ProductNotPurchasedException(String message) {
        super(message);
    }
}
