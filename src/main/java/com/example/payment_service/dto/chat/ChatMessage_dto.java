package com.example.payment_service.dto.chat;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage_dto {
    private String sender;
    private String receiver;
    private String content;
    private Date timestamp;
}