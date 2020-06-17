package com.tim26.ChatService.service;

import com.tim26.ChatService.dto.MessageDTO;
import com.tim26.ChatService.model.Message;

import java.security.Principal;
import java.util.List;

public interface ChatService {
    void save(Message message);
    boolean sendMessage(MessageDTO messageDTO, Principal p);
    List<MessageDTO> findAllReciever(Principal p);
    List<MessageDTO> findAllSender(Principal p);
}
