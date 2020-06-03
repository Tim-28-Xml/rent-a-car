package com.tim26.AuthenticationService.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class EndUser extends User {

    @Column
    private String firstname;

    @Column
    private String lastname;

    public EndUser() {
    }

    public EndUser(String email, String password, String username) {
        super(email, password, username);
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
