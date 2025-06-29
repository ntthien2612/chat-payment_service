package com.example.payment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payment_service.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
