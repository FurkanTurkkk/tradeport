package com.furkan.tradeport.exceptionhandler;

import com.furkan.tradeport.ErrorResponse;
import com.furkan.tradeport.exception.ProductAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(ProductAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleProductAlreadyExistException(ProductAlreadyExistException e){
        ErrorResponse error = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
