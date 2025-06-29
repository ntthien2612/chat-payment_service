package com.example.payment_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.payment_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    Optional<User> findByUsernameOrEmail(String username, String email);
    
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.todos")
    List<User> findAllWithTodos();

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.todos WHERE u.id = :id")
    Optional<User> findByIdWithTodos(@Param("id") Long id);
}
