package com.tim26.Zuul.zuulserver.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReqAdDto {
    private Long id;
    private String owner;
    private String creator;
    private LocalDateTime creationTime;
    private LocalDateTime reservationTime;
    private RequestStatus requestStatus;
    private List<AdDTO> ads = new ArrayList<>();
    private double price;

    public ReqAdDto() {
    }

    public ReqAdDto(ViewRequestDTO viewRequestDTO) {
        this.id = viewRequestDTO.getId();
        this.owner = viewRequestDTO.getOwner();
        this.creator = viewRequestDTO.getCreator();
        this.creationTime = viewRequestDTO.getCreationTime();
        this.reservationTime = viewRequestDTO.getReservationTime();
        this.requestStatus = viewRequestDTO.getRequestStatus();
        this.price = viewRequestDTO.getPrice();
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

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public List<AdDTO> getAds() {
        return ads;
    }

    public void setAds(List<AdDTO> ads) {
        this.ads = ads;
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
