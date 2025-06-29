package com.example.payment_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_service.dto.todo_dto.TodoRequest;
import com.example.payment_service.dto.todo_dto.TodoResponse;
import com.example.payment_service.services.todo_service.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody @Valid TodoRequest request) {
        return ResponseEntity.ok(todoService.createTodo(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long id, @RequestBody @Valid TodoRequest request) {
        return ResponseEntity.ok(todoService.updateTodo(id, request));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getTodos() {
        List<TodoResponse> todos = todoService.getTodosByCurrentUser();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<TodoResponse>> getTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TodoResponse> todosPage = todoService.getTodosByCurrentUser(pageable);
        return ResponseEntity.ok(todosPage);
    }

}