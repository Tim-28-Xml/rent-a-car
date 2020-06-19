package com.tim26.ChatService.controller;

import com.tim26.ChatService.dto.MessageDTO;
import com.tim26.ChatService.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChatController {

    @Autowired
    ChatService chatService;

    @GetMapping(value = "/test")
    public String test() {
        System.out.println("hello secured");
        return "Hello svet from chat service";
    }

    @PostMapping
    public ResponseEntity sendMessage(MessageDTO messageDTO, Principal p){
        boolean isSent = chatService.sendMessage(messageDTO, p);

        if(isSent){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/sender")
    public ResponseEntity<List<MessageDTO>> getAllSenders(Principal p){
        List<MessageDTO> messageDTOS = chatService.findAllSender(p);
        return new ResponseEntity(messageDTOS, HttpStatus.OK);
    }

    @GetMapping("/reciever")
    public ResponseEntity<List<MessageDTO>> getAllRecievers(Principal p){
        List<MessageDTO> messageDTOS = chatService.findAllReciever(p);
        return new ResponseEntity(messageDTOS, HttpStatus.OK);
    }

    @GetMapping("/people")
    public ResponseEntity<List<String>> getAllPeople(Principal p){
        List<String> people = chatService.findAllPeople(p);
        return new ResponseEntity(people, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<MessageDTO>> getAllPeople(@PathVariable String username, Principal p){
        List<MessageDTO> chat = chatService.findAllReceivedByUser(username, p);
        return new ResponseEntity(chat, HttpStatus.OK);
    }

}
