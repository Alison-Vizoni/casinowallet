package com.casinowallet.casinowallet.service.exceptions;

public class MethodNotImplementedException extends RuntimeException {

    public MethodNotImplementedException(String message){
        super(message);
    }
}
