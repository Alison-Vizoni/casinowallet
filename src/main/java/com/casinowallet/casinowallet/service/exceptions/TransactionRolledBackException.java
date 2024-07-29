package com.casinowallet.casinowallet.service.exceptions;

public class TransactionRolledBackException extends RuntimeException {
    public TransactionRolledBackException(String message) {
        super(message);
    }
}
