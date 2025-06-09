package com.example.payment_service.services.product_service;

import java.util.List;

import com.example.payment_service.dto.product_dto.ProductRequest;
import com.example.payment_service.dto.product_dto.ProductResponse;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    List<ProductResponse> getAll();
    ProductResponse getById(Long id);
    ProductResponse update(Long id, ProductRequest request);
    void delete(Long id);
}
