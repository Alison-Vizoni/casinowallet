package com.casinowallet.casinowallet.service.exceptions;

public class InvalidAccessException extends RuntimeException {
    public  InvalidAccessException(String message) {
        super(message);
    }
}
