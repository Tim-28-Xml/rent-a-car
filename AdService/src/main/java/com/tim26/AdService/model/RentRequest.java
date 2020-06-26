package com.tim26.AdService.model;

//import com.tim26.AdService.adapter.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "RentRequest", namespace = "http://localhost:8084/adservice-schema")
//@XmlRootElement(name = "rentRequestClass")
public class RentRequest {

    @Id
    //@XmlElement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "rentRequests")
    //@XmlElement
    private List<Ad> ads = new ArrayList<>();

    @Column
    //@XmlElement
    //@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate reqStartDate;

    @Column
    //@XmlElement
    //@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate reqEndDate;

    public RentRequest() {

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
