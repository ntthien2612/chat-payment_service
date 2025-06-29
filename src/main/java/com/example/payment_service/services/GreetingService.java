package com.example.payment_service.services;

import org.springframework.stereotype.Service;

import com.example.payment_service.dto.Greeting;

@Service
public class GreetingService {
    public Greeting getGreeting() {
        return new Greeting("Hello World");
    }
}