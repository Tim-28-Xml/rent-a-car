package com.tim26.RentRequestService.model;

import com.tim26.RentRequestService.dto.RentRequestDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class RentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private RequestStatus requestStatus;

    @OneToMany
    @MapKey(name = "id")
    private List<AdDateRange> adsWithDates = new ArrayList<>();

    @ManyToOne
    private User creator;

    @ManyToOne
    private User owner;

    @Column
    private LocalDateTime creationTime;

    @Column
    private LocalDateTime reservationTime;

    public RentRequest() {
        requestStatus = RequestStatus.PENDING;
    }

    public RentRequest(RentRequestDTO rentRequestDTO, String creator){
        this.owner = new User(rentRequestDTO.getOwner());
        this.creationTime = rentRequestDTO.getCreationTime();
        this.adsWithDates = rentRequestDTO.getAdsWithDates();
        this.requestStatus = RequestStatus.PENDING;
        this.creator = new User(creator);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public List<AdDateRange> getAdsWithDates() {
        return adsWithDates;
    }

    public void setAdsWithDates(List<AdDateRange> adsWithDates) {
        this.adsWithDates = adsWithDates;
    }
}
