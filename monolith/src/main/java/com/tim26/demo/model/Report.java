package com.tim26.demo.model;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String text;

    @Column
    private double startKM;

    @Column
    private double endKM;

    @ManyToOne()
    private User user;

    @Column
    private boolean paid;


    @ManyToOne
    private Ad ad;

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
