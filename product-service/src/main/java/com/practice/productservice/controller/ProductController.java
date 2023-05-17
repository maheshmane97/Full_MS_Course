package com.practice.productservice.controller;

import com.practice.productservice.model.ProductDTO;
import com.practice.productservice.model.ProductResponse;
import com.practice.productservice.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Log4j2
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Integer> addProduct(@RequestBody ProductDTO dto) {
        Integer prodId = productService.addProduct(dto);
        return new ResponseEntity<>(prodId, HttpStatus.CREATED);
    }

    @GetMapping("/{prodId}")
    public ResponseEntity<ProductResponse> getById(@PathVariable("prodId") Integer prodId) {
        ProductResponse response = productService.getById(prodId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/reducequanity/{prodId}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("prodId") Integer prodId, @RequestParam Integer quantity) {
        log.info("Reduced Quantity Controller quantity {} with id {}", quantity, prodId);
        productService.reduceQuantity(prodId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
