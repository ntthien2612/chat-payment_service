package com.example.payment_service.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_service.dto.chat.ChatMessage_dto;
import com.example.payment_service.services.chat_service.ChatService;

@RestController
public class ChatController {

    @Autowired
    private KafkaTemplate<String, ChatMessage_dto> kafkaTemplate;

    @Autowired
    private org.springframework.data.redis.core.RedisTemplate<String, String> redisTemplate;

    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    private static final String TOPIC = "chat-messages";

    @MessageMapping("/private-message")
    public void sendPrivateMessage(@Payload ChatMessage_dto message, Principal principal) {
        if (principal == null) {
            System.out.println("❌ Principal is null, WebSocket not authenticated!");
            return;
        }
        System.out.println("✅ Principal: " + principal.getName());
        System.out.println("Sending private message: " + message);
        kafkaTemplate.send(TOPIC, message.getReceiver(), message); // tốt hơn nên gửi key theo receiver
    }

    @GetMapping("/api/messages/history")
    public List<ChatMessage_dto> getMessageHistory(
            @RequestParam String receiver) {

        return chatService.findChatHistory(receiver);
    }

    @GetMapping("/redis-test")
    public String testRedis() {
        try {
            redisTemplate.opsForValue().set("health", "ok");
            return "✅ Redis OK: " + redisTemplate.opsForValue().get("health");
        } catch (Exception e) {
            return "❌ Redis ERROR: " + e.getMessage();
        }
    }
}