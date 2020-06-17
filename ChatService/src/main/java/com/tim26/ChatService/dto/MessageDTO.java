package com.tim26.ChatService.dto;

import com.tim26.ChatService.model.Message;

public class MessageDTO {

    private String content;
    private String sender;
    private String reciever;
    private String time;

    public MessageDTO(){

    }

    public MessageDTO(String content, String sender, String reciever, String time){
        this.content = content;
        this.sender = sender;
        this.reciever = reciever;
        this.time = time;
    }

    public MessageDTO(Message message){
        this(message.getContent(), message.getSender().getUsername(),message.getReceiver().getUsername(), message.getTime().toString());
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }
}
