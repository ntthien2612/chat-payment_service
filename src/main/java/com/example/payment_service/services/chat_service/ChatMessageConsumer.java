package com.example.payment_service.services.chat_service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.example.payment_service.dto.chat.ChatMessage_dto;
import com.example.payment_service.entity.ChatMessage;
import com.example.payment_service.repository.ChatMessageRepository;

@Component
public class ChatMessageConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageConsumer(SimpMessagingTemplate messagingTemplate,
            ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "chat-messages", groupId = "chat-group")
    public void listen(ChatMessage_dto message) {
        System.out.println("📥 Kafka received: " + message);
        // Lưu tin nhắn vào cơ sở dữ liệu (nếu cần)
        ChatMessage chatMessageToSave = new ChatMessage();
        chatMessageToSave.setSender(message.getSender());
        chatMessageToSave.setReceiver(message.getReceiver());   
        chatMessageToSave.setContent(message.getContent());
        chatMessageToSave.setTimestamp(message.getTimestamp());
        chatMessageRepository.save(chatMessageToSave);
        // Push message tới WebSocket (1-1)
        messagingTemplate.convertAndSendToUser(
                message.getReceiver(), // Tên người nhận (username)
                "/queue/messages", // Đích đón ở client
                message // Nội dung
        );
    }
}
