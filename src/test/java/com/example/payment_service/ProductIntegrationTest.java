package com.example.payment_service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.example.payment_service.dto.product_dto.ProductRequest;
import com.example.payment_service.dto.product_dto.ProductResponse;
import com.example.payment_service.services.product_service.ProductServiceImpl;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProductIntegrationTest {

    @Autowired
    private ProductServiceImpl productService;
    
    @Test
    void testInsertProduct() {
        ProductRequest request = new ProductRequest("Test Product", 100.0);
        ProductResponse created = productService.create(request);

        assertEquals("Test Product", created.getName());
        assertEquals(100.0, created.getPrice());
    }
}