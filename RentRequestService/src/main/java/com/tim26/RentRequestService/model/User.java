package com.tim26.RentRequestService.model;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class User {
    @Id
    private String username;

    public User(String username) {
        this.username = username;
    }

    public User(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
