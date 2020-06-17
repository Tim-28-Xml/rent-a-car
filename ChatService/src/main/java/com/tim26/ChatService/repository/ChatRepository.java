package com.tim26.ChatService.repository;

import com.tim26.ChatService.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Message, Long> {
    Message findById(long id);
    Message save(Message message);
}
