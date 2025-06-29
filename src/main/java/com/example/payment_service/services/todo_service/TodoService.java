package com.example.payment_service.services.todo_service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.payment_service.dto.todo_dto.TodoRequest;
import com.example.payment_service.dto.todo_dto.TodoResponse;

public interface TodoService {
    TodoResponse createTodo(TodoRequest request);

    List<TodoResponse> getTodosByUser(Long userId);

    TodoResponse updateTodo(Long id, TodoRequest request);

    List<TodoResponse> getTodosByCurrentUser();

    Page<TodoResponse> getTodosByCurrentUser(Pageable pageable);
}