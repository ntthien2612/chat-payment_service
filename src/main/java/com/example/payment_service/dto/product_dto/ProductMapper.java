package com.example.payment_service.dto.product_dto;

import com.example.payment_service.entity.Product;

public class ProductMapper {
    public static Product toEntity(ProductRequest request) {
        return new Product(null, request.getName(), request.getPrice());
    }

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }
}
