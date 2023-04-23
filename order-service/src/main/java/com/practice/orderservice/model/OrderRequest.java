package com.practice.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private Integer productId;
    private Integer totalAmount;
    private Integer quantity;
    private PaymentMode paymentMode;
}
