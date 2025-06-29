package com.example.payment_service.services.user_service;

import java.util.List;

import com.example.payment_service.dto.chat.ChatUsers;
import com.example.payment_service.dto.user_dto.CurrentUserResponse;
import com.example.payment_service.dto.user_dto.LoginRequest;
import com.example.payment_service.dto.user_dto.LoginResponse;
import com.example.payment_service.dto.user_dto.UserRequest;
import com.example.payment_service.dto.user_dto.UserResponse;

public interface  UserService {
    UserResponse register(UserRequest request);
    LoginResponse login(LoginRequest request);
    UserResponse createUser(UserRequest request);
    List<UserResponse> getAllUsers();
    List<ChatUsers> getAllChatUsers();
    UserResponse getUserById(Long id);
    CurrentUserResponse getCurrentUser();
}
