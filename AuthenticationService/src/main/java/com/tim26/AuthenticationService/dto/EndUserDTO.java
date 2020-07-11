package com.tim26.AuthenticationService.dto;

import com.tim26.AuthenticationService.model.EndUser;

public class EndUserDTO {

    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String password;

    public EndUserDTO(String username, String email, String firstname, String lastname,String password) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public EndUserDTO(EndUser endUser){
        this(endUser.getUsername(), endUser.getEmail(), endUser.getFirstname(), endUser.getLastname(),endUser.getPassword());
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
