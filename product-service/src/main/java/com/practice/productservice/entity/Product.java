package com.practice.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer prodId;
    @Column(name = "PRODUCT_NAME")
    private String prodName;
    @Column(name = "PRICE")
    private Integer price;
    @Column(name = "QUANTITY")
    private Integer quantity;
}
