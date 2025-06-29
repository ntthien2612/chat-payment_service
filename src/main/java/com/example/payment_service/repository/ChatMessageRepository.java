package com.example.payment_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.payment_service.entity.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    @Query("SELECT m FROM ChatMessage m WHERE " +
       "(m.sender = :sender AND m.receiver = :receiver) OR " +
       "(m.sender = :receiver AND m.receiver = :sender) " +
       "ORDER BY m.timestamp ASC")
    List<ChatMessage> findChatHistory(@Param("sender") String sender,
                                  @Param("receiver") String receiver);
}