package com.tim26.ChatService.service;

import com.tim26.ChatService.dto.MessageDTO;
import com.tim26.ChatService.model.Message;
import com.tim26.ChatService.model.User;
import com.tim26.ChatService.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    UserService userService;

    @Override
    public void save(Message message) {
        chatRepository.save(message);
    }

    public boolean sendMessage(MessageDTO messageDTO, Principal p){

        User reciever = userService.findByUsername(messageDTO.getSender());
        User sender = userService.findByUsername(p.getName());

        if(reciever == null || sender == null){
            return false;
        }

        Message message = new Message(messageDTO.getContent(), sender, reciever);
        message.setTime(LocalDateTime.now());
        save(message);

        return true;
    }

    @Override
    public List<MessageDTO> findAllReciever(Principal p) {
        List<Message> allMessages = chatRepository.findAll();
        List<MessageDTO> allReciever = new ArrayList<>();

        for(Message message: allMessages){
            if(message.getReceiver().equals(p.getName())) {
                MessageDTO messageDTO = new MessageDTO(message);
                allReciever.add(messageDTO);
            }
        }

        return allReciever;
    }

    @Override
    public List<MessageDTO> findAllSender(Principal p) {
        List<Message> allMessages = chatRepository.findAll();
        List<MessageDTO> allSender = new ArrayList<>();

        for(Message message: allMessages){
            if(message.getSender().equals(p.getName())) {
                MessageDTO messageDTO = new MessageDTO(message);
                allSender.add(messageDTO);
            }
        }

        return allSender;
    }
}
