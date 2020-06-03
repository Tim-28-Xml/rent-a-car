package com.tim26.AuthenticationService.dto;

import com.tim26.AuthenticationService.model.Agent;

public class AgentDTO {

    private String username;
    private String name;
    private String email;
    private int mbr;
    private String address;

    public AgentDTO(){

    }

    public AgentDTO(String username, String name, String email, int mbr, String address) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.mbr = mbr;
        this.address = address;
    }

    public AgentDTO(Agent agent){
        this(agent.getUsername(), agent.getName(), agent.getEmail(), agent.getMbr(), agent.getAddress());
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

}
