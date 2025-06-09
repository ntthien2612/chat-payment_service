package com.example.payment_service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.payment_service.dto.product_dto.ProductRequest;
import com.example.payment_service.dto.product_dto.ProductResponse;
import com.example.payment_service.entity.Product;
import com.example.payment_service.repository.ProductRepository;
import com.example.payment_service.services.product_service.ProductServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setup() {
        product = new Product(1L, "Test Product", 100.0);
    }

    @Test
    void testCreateProduct() {
        ProductRequest request = new ProductRequest("Test Product", 100.0);

        Mockito.when(productRepository.save(Mockito.any(Product.class)))
               .thenReturn(product);

        ProductResponse created = productService.create(request);

        assertEquals("Test Product", created.getName());
        assertEquals(100.0, created.getPrice());
    }
}