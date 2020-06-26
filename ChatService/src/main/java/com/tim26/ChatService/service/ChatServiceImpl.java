package com.tim26.ChatService.service;

import com.tim26.ChatService.dto.MessageDTO;
import com.tim26.ChatService.model.Message;
import com.tim26.ChatService.model.User;
import com.tim26.ChatService.repository.ChatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private static final Logger LOGGER= LoggerFactory.getLogger(ChatService.class);

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    UserService userService;

    @Override
    public void save(Message message) {
        chatRepository.save(message);
    }

    public boolean sendMessage( MessageDTO messageDTO, Principal p){

        User receiver = userService.findByUsername(messageDTO.getReceiver());
        User sender = userService.findByUsername(p.getName());

        if(receiver == null || sender == null){
            LOGGER.error("Reciever or sender is null.");
            return false;
        }

        Message message = new Message(messageDTO.getContent(), sender, receiver, false);
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

    @Override
    public List<String> findAllPeople(Principal p) {
        User user = userService.findByUsername(p.getName());

        List<Message> allMessages = chatRepository.findAllByReceiver(user);
        List<String> people = new ArrayList<>();

        for(Message msg: allMessages){
            if(!people.contains(msg.getSender().getUsername())) {
                people.add(msg.getSender().getUsername());
            }
        }

        return people;
    }

    @Override
    public List<MessageDTO> findAllReceivedByUser(String username, Principal p) {
        User user = userService.findByUsername(p.getName());
        List<Message> allMyReceived = chatRepository.findAllByReceiver(user);
        List<MessageDTO> userChatAll = new ArrayList<>();

        for(Message message: allMyReceived){
            if(message.getSender().getUsername().equals(username)){
                MessageDTO messageDTO = new MessageDTO(message);
                userChatAll.add(messageDTO);
            }
        }

        List<Message> allMySent = chatRepository.findAllBySender(user);
        for(Message message: allMySent){
            if(message.getReceiver().getUsername().equals(username)){
                MessageDTO messageDTO = new MessageDTO(message);
                userChatAll.add(messageDTO);
            }
        }

        return userChatAll;
    }

    @Override
    public String hasAnyNewMsgs(Principal p) {
        User user = userService.findByUsername(p.getName());
        List<Message> allMyReceived = chatRepository.findAllByReceiver(user);
        int num = 0;

        for (Message message: allMyReceived){
            if(!message.isRead()){
                num += 1;
            }
        }
        String number = Integer.toString(num);
        return number;
    }

    @Override
    public String readMessage(String username, Principal p) {
        User user = userService.findByUsername(p.getName());
        List<Message> getMyRecieved = chatRepository.findAllByReceiver(user);

        for (Message message: getMyRecieved){
            if(!message.isRead()){
                if(message.getSender().getUsername().equals(username)){
                    message.setRead(true);
                    save(message);
                }
            }
        }
        String number = hasAnyNewMsgs(p);
        return number;
    }


}
