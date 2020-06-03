package com.tim26.AuthenticationService.model;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin() {
    }

    public Admin(String email, String password, String username) {
        super(email, password, username);
    }
}
