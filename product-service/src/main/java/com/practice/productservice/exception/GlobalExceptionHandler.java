package com.practice.productservice.exception;

import com.practice.productservice.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductServiceException.class)
    public ResponseEntity<ExceptionResponse> handleProductServiceException(ProductServiceException exception){
        return new ResponseEntity<>(new ExceptionResponse().builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(), HttpStatus.NOT_FOUND);
    }
}
