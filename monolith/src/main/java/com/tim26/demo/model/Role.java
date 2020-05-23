package com.tim26.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role implements GrantedAuthority {

    @Id
    private String name;

    @ManyToMany
    private final List<Permission> permissions = new ArrayList<>();

    @Override
    public String getAuthority() {
        return name;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }
}
