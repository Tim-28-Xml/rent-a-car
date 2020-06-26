package com.tim26.ChatService.controller;

import com.tim26.ChatService.dto.MessageDTO;
import com.tim26.ChatService.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('SEND_MESSAGE')")
    @PostMapping
    public ResponseEntity sendMessage(@RequestBody MessageDTO messageDTO, Principal p){
        boolean isSent = chatService.sendMessage(messageDTO, p);

        if(isSent){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyRole('USER','AGENT')")
    @GetMapping("/sender")
    public ResponseEntity<List<MessageDTO>> getAllSenders(Principal p){
        List<MessageDTO> messageDTOS = chatService.findAllSender(p);
        return new ResponseEntity(messageDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','AGENT')")
    @GetMapping("/reciever")
    public ResponseEntity<List<MessageDTO>> getAllRecievers(Principal p){
        List<MessageDTO> messageDTOS = chatService.findAllReciever(p);
        return new ResponseEntity(messageDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','AGENT')")
    @GetMapping("/people")
    public ResponseEntity<List<String>> getAllPeople(Principal p){
        List<String> people = chatService.findAllPeople(p);
        return new ResponseEntity(people, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','AGENT')")
    @GetMapping("/{username}")
    public ResponseEntity<List<MessageDTO>> getAllPeople(@PathVariable String username, Principal p){
        List<MessageDTO> chat = chatService.findAllReceivedByUser(username, p);
        return new ResponseEntity(chat, HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<String> getNewNumber(Principal p){
        String number  = chatService.hasAnyNewMsgs(p);
        return new ResponseEntity(number, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','AGENT')")
    @GetMapping("/read/{username}")
    public ResponseEntity<String> readMessage(@PathVariable String username, Principal p){
        String num = chatService.readMessage(username,p);

        return new ResponseEntity<>(num, HttpStatus.OK);

    }

}
