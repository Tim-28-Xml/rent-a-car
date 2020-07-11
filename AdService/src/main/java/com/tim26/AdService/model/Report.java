package com.tim26.AdService.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "Report", namespace = "http://localhost:8084/adservice-schema")
//@XmlRootElement(name = "reportClass")
public class Report {

    @Id
    @XmlElement
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    //@XmlElement
    private String text;

    @Column
    //@XmlElement
    private double startKM;

    @Column
    //@XmlElement
    private double endKM;

    @ManyToOne
    //@XmlElement
    private Ad ad;

    @ManyToOne()
    private User user;

    @Column
    private boolean paid;

    public Report() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getStartKM() {
        return startKM;
    }

    public void setStartKM(double startKM) {
        this.startKM = startKM;
    }

    public double getEndKM() {
        return endKM;
    }

    public void setEndKM(double endKM) {
        this.endKM = endKM;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}