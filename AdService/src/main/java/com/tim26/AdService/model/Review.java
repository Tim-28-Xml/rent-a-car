package com.tim26.AdService.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "Review", namespace = "http://localhost:8084/adservice-schema")
//@XmlRootElement(name = "reviewClass")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@XmlElement
    private Long id;

    @ManyToOne
    //@XmlElement
    private Ad ad;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}
