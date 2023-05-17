package com.practice.productservice.service;

import com.practice.productservice.entity.Product;
import com.practice.productservice.model.ProductDTO;
import com.practice.productservice.model.ProductResponse;

import java.util.List;

public interface ProductService {
    Integer addProduct(ProductDTO dto);

    List<Product> getAllProduct();

    ProductResponse getById(Integer prodId);

    void reduceQuantity(Integer prodId, Integer quantity);
}
