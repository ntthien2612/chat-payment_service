package com.example.payment_service.services.todo_service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.payment_service.common.CurrentUser;
import com.example.payment_service.dto.todo_dto.TodoMapper;
import com.example.payment_service.dto.todo_dto.TodoRequest;
import com.example.payment_service.dto.todo_dto.TodoResponse;
import com.example.payment_service.entity.Todo;
import com.example.payment_service.entity.User;
import com.example.payment_service.exception.ResourceNotFoundException;
import com.example.payment_service.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoMapper todoMapper;

    @Autowired
    private final CurrentUser currentUser;

    public TodoServiceImpl(TodoRepository todoRepository, CurrentUser currentUser) {
        this.todoRepository = todoRepository;
        this.currentUser = currentUser;
    }

    @Override
    public TodoResponse createTodo(TodoRequest request) {

        User user = currentUser.getCurrentUser();

        Todo todo = todoMapper.toEntity(request);
        todo.setUser(user);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.toResponse(savedTodo);
    }

    @Override
    public List<TodoResponse> getTodosByUser(Long userId) {
        List<Todo> todos = todoRepository.findByUserId(userId);
        return todos.stream()
                .map(t -> new TodoResponse(t.getId(), t.getTitle(), t.getDescription(), t.isCompleted()))
                .collect(Collectors.toList());
    }

    @Override
    public TodoResponse updateTodo(Long id, TodoRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));

        todo.setTitle(request.getTitle() != null ? request.getTitle() : todo.getTitle());
        todo.setCompleted(request.isCompleted());

        Todo updated = todoRepository.save(todo);
        return todoMapper.toResponse(updated);
    }

    @Override
    public List<TodoResponse> getTodosByCurrentUser() {
        Long userId = currentUser.getCurrentUserId();
        List<Todo> todos = todoRepository.findByUserId(userId);
        return todos.stream()
                .map(todo -> new TodoResponse(todo.getId(), todo.getTitle(), todo.getDescription(), todo.isCompleted()))
                .collect(Collectors.toList());
    }

    @Override
    public Page<TodoResponse> getTodosByCurrentUser(Pageable pageable) {
        Long userId = currentUser.getCurrentUserId();
        Page<Todo> todosPage = todoRepository.findByUserId(userId, pageable);

        // Chuyển Page<Todo> thành Page<TodoResponse>
        return todosPage.map(todo -> new TodoResponse(todo.getId(), todo.getTitle(), todo.getDescription(), todo.isCompleted()));
    }
}