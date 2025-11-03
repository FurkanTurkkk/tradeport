package com.furkan.tradeport.exception;

public class AddressEntityAlreadyExistException extends RuntimeException {
    public AddressEntityAlreadyExistException(String message) {
        super(message);
    }
}
