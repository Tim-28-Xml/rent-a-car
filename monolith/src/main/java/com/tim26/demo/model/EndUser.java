package com.tim26.demo.model;

import javax.persistence.Entity;

@Entity
public class EndUser extends User {

    private String firstname;
    private String lastname;

    public EndUser() {
    }

    public EndUser(Role role, String email, String password, String username, String firstname, String lastname) {
        super(role, email, password, username);
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
