package com.practice.productservice.service;

import com.practice.productservice.entity.Product;
import com.practice.productservice.exception.ProductServiceException;
import com.practice.productservice.model.ProductDTO;
import com.practice.productservice.model.ProductResponse;
import com.practice.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        Product product = new Product(1, "Samsung", 15000, 5);
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));
    }

    @Test
    void testGetById() {
        ProductResponse prod = productService.getById(1);
        assertEquals(1, prod.getProdId());
        assertThrows(ProductServiceException.class, () -> productService.getById(2));
    }

    @Test
    void testAddProduct() {
        Product product = new Product(1, "Samsung", 15000, 5);
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);
        ProductDTO dto = new ProductDTO("Samsung", 15000, 5);
        Integer prodId = productService.addProduct(dto);
        assertEquals(1, prodId);
    }

    @Test
    void testGetAllProduct() {
        Product product = new Product(1, "Samsung", 15000, 5);
        Product product1 = new Product(2, "Samsung", 15000, 5);
        List<Product> prodList = Arrays.asList(product1, product);
        Mockito.when(productService.getAllProduct()).thenReturn(prodList);
    }

    @Test
    void testReduceQuantity() {// Mocked Product object
        Product product = new Product(1, "Samsung", 15000, 5);
        productService.reduceQuantity(1, 1);
        assertEquals(5, product.getQuantity());

        ProductServiceException ex = assertThrows(ProductServiceException.class, () -> productService.reduceQuantity(1, 5));
        assertEquals("INSUFFICIENT_QUANTITY", ex.getErrorCode());
    }

}