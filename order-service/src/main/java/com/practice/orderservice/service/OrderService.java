package com.practice.orderservice.service;

import com.practice.orderservice.model.OrderRequest;

public interface OrderService {
    Integer placeOrder(OrderRequest orderRequest);
}
