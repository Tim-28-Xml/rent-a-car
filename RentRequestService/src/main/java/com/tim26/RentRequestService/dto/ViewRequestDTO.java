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
    private List<AdDateRange> adsWithDates = new ArrayList<>();
    private RequestStatus requestStatus;

    public ViewRequestDTO() {
    }

    public ViewRequestDTO(RentRequest rentRequest) {
        this.id = rentRequest.getId();
        this.owner = rentRequest.getOwner().getUsername();
        this.creator = rentRequest.getCreator().getUsername();
        this.creationTime = rentRequest.getCreationTime();
        this.adsWithDates = rentRequest.getAdsWithDates();
        this.requestStatus = rentRequest.getRequestStatus();
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
}

