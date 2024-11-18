package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.UnauthorizedException;

public class InvalidCredentialsException extends UnauthorizedException {
  public InvalidCredentialsException(String message) {
    super(message);
  }
}
