package com.tim26.demo.model;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin() {
    }

    public Admin(Role role, String email, String password, String username) {
        super(role, email, password, username);
    }
}
