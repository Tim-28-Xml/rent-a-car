package com.tim26.ChatService.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    private String username;

    public User(){

    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
