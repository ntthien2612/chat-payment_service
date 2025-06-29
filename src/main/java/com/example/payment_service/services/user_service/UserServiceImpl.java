package com.example.payment_service.services.user_service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.payment_service.config.CurrentUser;
import com.example.payment_service.config.JwtUtil;
import com.example.payment_service.dto.chat.ChatUsers;
import com.example.payment_service.dto.user_dto.CurrentUserResponse;
import com.example.payment_service.dto.user_dto.LoginRequest;
import com.example.payment_service.dto.user_dto.LoginResponse;
import com.example.payment_service.dto.user_dto.UserMapper;
import com.example.payment_service.dto.user_dto.UserRequest;
import com.example.payment_service.dto.user_dto.UserResponse;
import com.example.payment_service.entity.User;
import com.example.payment_service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

     @Autowired
    private final CurrentUser currentUser;

    public UserServiceImpl( CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public UserResponse register(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }
        // Encode the password before saving
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token, user.getUsername(), user.getEmail());
    }

    @Override
    public UserResponse createUser(UserRequest request) {

        User user = userMapper.toEntity(request);
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);

    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findByIdWithTodos(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAllWithTodos().stream()
                .map(user -> userMapper.toResponse(user))
                .collect(Collectors.toList());
    }

    @Override  
    public List<ChatUsers> getAllChatUsers() {
        return userRepository.findAll().stream()
                .map(user -> new ChatUsers(user.getId(), user.getUsername(), user.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public CurrentUserResponse getCurrentUser() {
        User user = currentUser.getCurrentUser();
        return new CurrentUserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}
