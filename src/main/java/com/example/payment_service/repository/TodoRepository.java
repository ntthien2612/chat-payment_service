package com.example.payment_service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payment_service.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
     List<Todo> findByUserId(Long userId);

     Page<Todo> findByUserId(Long userId, Pageable pageable);
}
