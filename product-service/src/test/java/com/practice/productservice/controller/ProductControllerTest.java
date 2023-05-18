package com.practice.productservice.controller;

import com.practice.productservice.model.ProductDTO;
import com.practice.productservice.model.ProductResponse;
import com.practice.productservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void addProduct() {
        ProductDTO dto = new ProductDTO("Samsung", 10000, 2);
        Integer prodId = productService.addProduct(dto);
        ResponseEntity<Integer> responseEntity = productController.addProduct(dto);
        assertEquals(prodId, responseEntity.getBody());
    }

    @Test
    void getById() {
        Integer prodId = 1;
        ProductResponse product1 = new ProductResponse(1, "Samsung", 10000, 2);
        Mockito.when(productService.getById(1)).thenReturn(product1);
        ResponseEntity<ProductResponse> prod = productController.getById(prodId);
        assertEquals(product1, prod.getBody());
    }

    @Test
    void reduceQuantity() {
        productService.reduceQuantity(1, 2);
        ResponseEntity<Void> response = productController.reduceQuantity(1, 2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}