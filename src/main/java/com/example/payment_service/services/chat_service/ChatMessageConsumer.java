package com.example.payment_service.services.chat_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.example.payment_service.dto.chat.ChatMessage_dto;
import com.example.payment_service.entity.ChatMessage;
import com.example.payment_service.repository.ChatMessageRepository;

@Component
public class ChatMessageConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageConsumer(SimpMessagingTemplate messagingTemplate,
            ChatMessageRepository chatMessageRepository,
            RedisTemplate<String, Object> redisTemplate) {
        this.chatMessageRepository = chatMessageRepository;
        this.messagingTemplate = messagingTemplate;
        this.redisTemplate = redisTemplate;
    }

    @KafkaListener(topics = "chat-messages", groupId = "chat-group")
    public void listen(ChatMessage_dto message) {
        System.out.println("Kafka received: " + message);

        // Lưu tin nhắn vào DB
        ChatMessage chatMessageToSave = new ChatMessage();
        chatMessageToSave.setSender(message.getSender());
        chatMessageToSave.setReceiver(message.getReceiver());
        chatMessageToSave.setContent(message.getContent());
        chatMessageToSave.setTimestamp(message.getTimestamp());
        chatMessageRepository.save(chatMessageToSave);

        // Xoá cache cho cả 2 chiều trò chuyện
        String key1 = "chatHistory::" + message.getSender() + ":" + message.getReceiver();
        String key2 = "chatHistory::" + message.getReceiver() + ":" + message.getSender();
        redisTemplate.delete(key1);
        redisTemplate.delete(key2);
        System.out.println("Cache cleared: " + key1 + " + " + key2);

        // Gửi tới WebSocket người nhận
        messagingTemplate.convertAndSendToUser(
                message.getReceiver(),
                "/queue/messages",
                message);
    }
}
