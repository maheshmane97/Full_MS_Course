package com.practice.orderservice.exception;

import com.practice.orderservice.external.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleProductServiceException(CustomException exception){
        return new ResponseEntity<>(new ExceptionResponse().builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(), HttpStatus.valueOf(exception.getStatus()));
    }
}
