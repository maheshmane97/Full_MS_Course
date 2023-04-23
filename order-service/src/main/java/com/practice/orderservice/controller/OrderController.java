package com.practice.orderservice.controller;

import com.practice.orderservice.model.OrderRequest;
import com.practice.orderservice.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/place")
    public ResponseEntity<Integer> placeOrder(@RequestBody OrderRequest orderRequest){
        Integer orderId=orderService.placeOrder(orderRequest);
        log.info("Order Controller order Placed with order id : {}", orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

}
