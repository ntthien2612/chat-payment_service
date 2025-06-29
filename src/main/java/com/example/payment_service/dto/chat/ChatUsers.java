package com.example.payment_service.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class ChatUsers {
    private Long id;
    private String username;
    private String email;
}
