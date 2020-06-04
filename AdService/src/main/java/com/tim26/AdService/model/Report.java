package com.tim26.AdService.model;

import javax.persistence.*;

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
}