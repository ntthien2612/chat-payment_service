package com.example.payment_service.services.chat_service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.payment_service.config.CurrentUser;
import com.example.payment_service.dto.chat.ChatMessage_dto;
import com.example.payment_service.entity.ChatMessage;
import com.example.payment_service.entity.User;
import com.example.payment_service.repository.ChatMessageRepository;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatMessageRepository chatMessageRepository;
    private final CurrentUser currentUser;

    public ChatServiceImpl(ChatMessageRepository chatMessageRepository, CurrentUser currentUser) {
        this.chatMessageRepository = chatMessageRepository;
        this.currentUser = currentUser;
    }   
    @Override
    public List<ChatMessage_dto> findChatHistory(String receiver) {
        User user = currentUser.getCurrentUser();

        List<ChatMessage> chatHistory = chatMessageRepository.findChatHistory(user.getEmail(), receiver);
        // Convert List<ChatMessage> to List<ChatMessage_dto>
        List<ChatMessage_dto> chatHistoryDto = chatHistory.stream()
            .map(message -> new ChatMessage_dto(
                message.getSender(),
                message.getReceiver(),
                message.getContent(),
                message.getTimestamp()
            ))
            .toList();
        // Return the converted list   
        return chatHistoryDto;
        
    }
    
}
