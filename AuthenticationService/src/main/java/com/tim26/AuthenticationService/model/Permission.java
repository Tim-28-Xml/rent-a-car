package com.tim26.AuthenticationService.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Permission implements GrantedAuthority {

    @Id
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
