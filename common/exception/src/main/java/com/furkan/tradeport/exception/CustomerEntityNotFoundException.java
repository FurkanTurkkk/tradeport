package com.furkan.tradeport.exception;

public class CustomerEntityNotFoundException extends RuntimeException {
    public CustomerEntityNotFoundException(String message) {
        super(message);
    }
}
