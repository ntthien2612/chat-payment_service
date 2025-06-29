package com.example.payment_service.dto.user_dto;

import java.util.List;

import com.example.payment_service.dto.todo_dto.TodoResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private List<TodoResponse> todos;
}
