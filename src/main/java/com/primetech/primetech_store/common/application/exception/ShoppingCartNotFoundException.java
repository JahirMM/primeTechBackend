package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.NotFoundException;

public class ShoppingCartNotFoundException extends NotFoundException {
    public ShoppingCartNotFoundException(String message) {
        super(message);
    }
}
