package com.tim26.AdService.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "rentRequests")
    private List<Ad> ads = new ArrayList<>();

    @Column
    private LocalDate reqStartDate;

    @Column
    private LocalDate reqEndDate;

    RentRequest() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public LocalDate getReqStartDate() {
        return reqStartDate;
    }

    public void setReqStartDate(LocalDate reqStartDate) {
        this.reqStartDate = reqStartDate;
    }

    public LocalDate getReqEndDate() {
        return reqEndDate;
    }

    public void setReqEndDate(LocalDate reqEndDate) {
        this.reqEndDate = reqEndDate;
    }
}
