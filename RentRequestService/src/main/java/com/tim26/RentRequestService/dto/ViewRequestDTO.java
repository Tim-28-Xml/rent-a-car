package com.tim26.RentRequestService.dto;

import com.tim26.RentRequestService.model.AdDateRange;
import com.tim26.RentRequestService.model.RentRequest;
import com.tim26.RentRequestService.model.RequestStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public ViewRequestDTO(RentRequest rentRequest) {
        this.id = rentRequest.getId();
        this.owner = rentRequest.getOwner().getUsername();
        this.creator = rentRequest.getCreator().getUsername();
        this.creationTime = rentRequest.getCreationTime();
        this.adsWithDates = rentRequest.getAdsWithDates();
        this.requestStatus = rentRequest.getRequestStatus();
        this.reservationTime = rentRequest.getReservationTime();
        this.price = rentRequest.getPrice();
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

