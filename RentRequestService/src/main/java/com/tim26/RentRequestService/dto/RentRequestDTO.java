package com.tim26.RentRequestService.dto;

import com.tim26.RentRequestService.model.RentRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentRequestDTO {
    private List<Long> ads = new ArrayList<>();
    private String owner;
    private LocalDateTime creationTime;

    public RentRequestDTO() {
    }

    public RentRequestDTO(RentRequest r) {

        this.owner = r.getOwner().getUsername();
        this.creationTime = r.getCreationTime();
        this.ads = r.getAds();
    }

    public List<Long> getAds() {
        return ads;
    }

    public void setAds(List<Long> ads) {
        this.ads = ads;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
