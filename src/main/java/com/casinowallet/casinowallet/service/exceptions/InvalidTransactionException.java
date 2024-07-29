package com.casinowallet.casinowallet.service.exceptions;

public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(String message){
        super(message);
    }
}
