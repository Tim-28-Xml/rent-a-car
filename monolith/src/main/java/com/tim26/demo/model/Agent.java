package com.tim26.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Agent extends User {

    private int mbr;

    private String name;
    private String address;
    private double quota;

    public Agent() {
        super();
        this.setRole(Role.AGENT);
    }

    public Agent(Role role, String email, String password, String username) {
        super(role, email, password, username);
    }

    public int getMbr() {
        return mbr;
    }

    public void setMbr(int mbr) {
        this.mbr = mbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getQuota() {
        return quota;
    }

    public void setQuota(double quota) {
        this.quota = quota;
    }
}
