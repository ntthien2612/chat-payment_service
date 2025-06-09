package com.example.payment_service.services.chat_service;

import java.util.List;

import com.example.payment_service.dto.chat.ChatMessage_dto;

public interface ChatService {

    // Define methods for chat service operations here
    List<ChatMessage_dto> findChatHistory(String receiver);
    // void sendMessage(ChatMessage_dto message);
    // void deleteMessage(Long messageId);

    
}
