package com.furkan.tradeport.exceptionhandler;

import com.furkan.tradeport.ErrorResponse;
import com.furkan.tradeport.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CustomerDomainException.class)
    public ResponseEntity<ErrorResponse> handleCustomerDomainException(CustomerDomainException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AddressEntityAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleAddressEntityAlreadyExistException(AddressEntityAlreadyExistException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(CustomerEntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerEntityNotFoundException(CustomerEntityNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IdNumberAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleIdNumberAlreadyExistException(IdNumberAlreadyExistException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
