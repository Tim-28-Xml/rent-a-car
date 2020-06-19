package com.tim26.RentRequestService.dto;

import com.tim26.RentRequestService.model.AdDateRange;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentRequestDTO {
    private List<AdDateRange> adsWithDates = new ArrayList<>();
    private String owner;
    private LocalDateTime creationTime;

    public RentRequestDTO() {
    }

    public List<AdDateRange> getAdsWithDates() {
        return adsWithDates;
    }

    public void setAdsWithDates(List<AdDateRange> adsWithDates) {
        this.adsWithDates = adsWithDates;
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
