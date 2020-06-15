package com.tim26.RentRequestService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentRequestDTO {
    private List<Long> ads = new ArrayList<>();
    private String owner;
    private LocalDateTime creationTime;

    public RentRequestDTO() {
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
