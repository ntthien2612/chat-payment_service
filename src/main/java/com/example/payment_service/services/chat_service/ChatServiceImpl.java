package com.example.payment_service.services.chat_service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.payment_service.config.CurrentUser;
import com.example.payment_service.dto.chat.ChatMessage_dto;
import com.example.payment_service.entity.ChatMessage;
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
    @Cacheable(value = "chatHistory", key = "#root.target.generateKey(#receiver)")
    public List<ChatMessage_dto> findChatHistory(String receiver) {
        String sender = currentUser.getCurrentUser().getEmail();
        String key = sender + ":" + receiver;

        System.out.println("Cache MISS querying DB with key: " + key);

        List<ChatMessage> chatHistory = chatMessageRepository.findChatHistory(sender, receiver);

        return chatHistory.stream()
                .map(msg -> new ChatMessage_dto(
                        msg.getSender(),
                        msg.getReceiver(),
                        msg.getContent(),
                        msg.getTimestamp()))
                .toList();
    }

    public String generateKey(String receiver) {
        return currentUser.getCurrentUser().getEmail() + ":" + receiver;
    }
}
