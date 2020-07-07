package com.tim26.Zuul.zuulserver.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ViewRequestDTO {
    private Long id;
    private String owner;
    private String creator;
    private LocalDateTime creationTime;
    private LocalDateTime reservationTime;
    private List<AdDateRange> adsWithDates = new ArrayList<>();
    private RequestStatus requestStatus;
    private double price;

    public ViewRequestDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public List<AdDateRange> getAdsWithDates() {
        return adsWithDates;
    }

    public void setAdsWithDates(List<AdDateRange> adsWithDates) {
        this.adsWithDates = adsWithDates;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

