package com.tim26.AuthenticationService.dto;

import com.tim26.AuthenticationService.model.Agent;

public class AgentDTO {

    private String username;
    private String name;
    private String email;
    private int mbr;
    private String address;
    private String password;

    public AgentDTO(){

    }

    public AgentDTO(String username, String name, String email, int mbr, String address,String password) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.mbr = mbr;
        this.address = address;
        this.password = password;
    }

    public AgentDTO(Agent agent){
        this(agent.getUsername(), agent.getName(), agent.getEmail(), agent.getMbr(), agent.getAddress(),agent.getPassword());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMbr() {
        return mbr;
    }

    public void setMbr(int mbr) {
        this.mbr = mbr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
