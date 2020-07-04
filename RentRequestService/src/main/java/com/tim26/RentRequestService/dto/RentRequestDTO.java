package com.tim26.RentRequestService.dto;

import com.tim26.RentRequestService.model.RentRequest;
import com.tim26.RentRequestService.model.AdDateRange;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentRequestDTO {
    private List<AdDateRange> adsWithDates = new ArrayList<>();
    private String owner;
    private LocalDateTime creationTime;
    private int price;

    public RentRequestDTO() {
    }

    public RentRequestDTO(RentRequest r) {

        this.owner = r.getOwner().getUsername();
        this.creationTime = r.getCreationTime();
        this.adsWithDates = r.getAdsWithDates();
        this.price = r.getPrice();
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
