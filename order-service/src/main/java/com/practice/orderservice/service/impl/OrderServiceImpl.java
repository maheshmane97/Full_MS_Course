package com.practice.orderservice.service.impl;

import com.practice.orderservice.entity.Order;
import com.practice.orderservice.external.client.ProductService;
import com.practice.orderservice.model.OrderRequest;
import com.practice.orderservice.repository.OrderRepository;
import com.practice.orderservice.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;
    @Override
    public Integer placeOrder(OrderRequest orderRequest) {
        log.info("Placing Order Request : {}", orderRequest);
        //Order Entity-> Save Data with status order CREATED
        //Product Service -> Block Products (Reduce the quantity)
        //Payment Service -> Payments -> Success-> COMPLETED, Else CANCELLED
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        log.info("Creating order with status : {}", "CREATED");
        Order order=Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        Order save = orderRepository.save(order);
        log.info("Order Placed with order Id : {}", save.getOrderId());
        return save.getOrderId();
    }
}
