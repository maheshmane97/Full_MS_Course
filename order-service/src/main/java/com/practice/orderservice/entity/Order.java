package com.practice.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ORDER_TABLE")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Column(name = "ORDER_DATE")
    private Instant orderDate;
    @Column(name = "ORDER_STATUS")
    private String orderStatus;
    @Column(name = "TOTAL_AMOUNT")
    private Integer amount;

}
