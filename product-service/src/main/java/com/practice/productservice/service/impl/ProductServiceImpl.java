package com.practice.productservice.service.impl;

import com.practice.productservice.entity.Product;
import com.practice.productservice.exception.ProductServiceException;
import com.practice.productservice.model.ProductDTO;
import com.practice.productservice.model.ProductResponse;
import com.practice.productservice.repository.ProductRepository;
import com.practice.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Integer addProduct(ProductDTO dto) {
        Product product = Product.builder().prodName(dto.getProdName()).price(dto.getPrice()).quantity(dto.getQuantity()).build();
        Integer prodId = productRepository.save(product).getProdId();
        log.info("Product Created");
        return prodId;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public ProductResponse getById(Integer prodId) {
        Product product = productRepository.findById(prodId).orElseThrow(() -> new ProductServiceException("Product not present with " + prodId, "PRODUCT_NOT_FOUND"));
        ProductResponse response = new ProductResponse();
        copyProperties(product, response);
        return response;
    }

    @Override
    public void reduceQuantity(Integer prodId, Integer quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity, prodId);
        Product product = productRepository.findById(prodId).orElseThrow(() -> new ProductServiceException("Product not present with " + prodId, "PRODUCT_NOT_FOUND"));
        if (product.getQuantity() < quantity) {
            throw new ProductServiceException("Product does not have sufficient quantity", "INSUFFICIENT_QUANTITY");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product Quantity Updated successfully..!");
    }
}
