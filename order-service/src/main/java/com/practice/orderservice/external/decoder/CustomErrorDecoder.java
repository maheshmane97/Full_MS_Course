package com.practice.orderservice.external.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.orderservice.exception.CustomException;
import com.practice.orderservice.external.response.ExceptionResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("::{}", response.request().url());
        log.info("::{}", response.request().headers());
        try {
            ExceptionResponse exceptionResponse = objectMapper.readValue(response.body().asInputStream(), ExceptionResponse.class);
            return new CustomException(exceptionResponse.getErrorMessage(), exceptionResponse.getErrorCode(), response.status());
        } catch (IOException e) {
            throw new CustomException("Ineternal Server Error", "INTERNAL_SERVER_ERROR", 500);
        }
    }
}
