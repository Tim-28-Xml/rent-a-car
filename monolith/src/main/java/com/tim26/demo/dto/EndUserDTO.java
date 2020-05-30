package com.tim26.demo.dto;

import com.tim26.demo.model.EndUser;

public class EndUserDTO {

    private String username;
    private String email;
    private String firstname;
    private String lastname;

    public EndUserDTO(String username, String email, String firstname, String lastname) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public EndUserDTO(EndUser endUser){
        this(endUser.getUsername(), endUser.getEmail(), endUser.getFirstname(), endUser.getLastname());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
