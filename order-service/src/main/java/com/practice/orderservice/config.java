package com.practice.orderservice;

import com.practice.orderservice.external.decoder.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean
    ErrorDecoder errorDecoder(){
        return new CustomErrorDecoder();
    }

}
