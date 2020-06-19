package com.tim26.ChatService.repository;

import com.tim26.ChatService.model.Message;
import com.tim26.ChatService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Message, Long> {
    Message findById(long id);
    Message save(Message message);
    List<Message> findAllByReceiver(User user);
    List<Message> findAllBySender(User user);
}
