package com.example.payment_service.dto.user_dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.payment_service.dto.todo_dto.TodoResponse;
import com.example.payment_service.entity.User;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        return user;
    }
    public UserResponse toResponse(User user) {

        return mapToUserResponse(user);
    }

    private UserResponse mapToUserResponse(User user) {
        List<TodoResponse> todoResponses = user.getTodos().stream()
                .map(todo -> new TodoResponse(todo.getId(), todo.getTitle(), todo.getDescription() , todo.isCompleted()))
                .collect(Collectors.toList());

        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), todoResponses);
    }
}
