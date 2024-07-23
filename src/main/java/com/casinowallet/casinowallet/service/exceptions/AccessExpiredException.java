package com.casinowallet.casinowallet.service.exceptions;

public class AccessExpiredException extends RuntimeException {
    public AccessExpiredException(String message) {
        super(message);
    }
}
