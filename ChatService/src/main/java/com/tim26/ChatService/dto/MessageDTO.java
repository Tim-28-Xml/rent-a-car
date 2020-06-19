package com.tim26.ChatService.dto;

import com.tim26.ChatService.model.Message;

public class MessageDTO {

    private String content;
    private String sender;
    private String reciever;
    private String time;
    private boolean read;

    public MessageDTO(){

    }

    public MessageDTO(String content, String sender, String reciever, String time, boolean read){
        this.content = content;
        this.sender = sender;
        this.reciever = reciever;
        this.time = time;
        this.read = read;
    }

    public MessageDTO(Message message){
        this(message.getContent(), message.getSender().getUsername(),message.getReceiver().getUsername(), message.getTime().toString(), message.isRead());
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
