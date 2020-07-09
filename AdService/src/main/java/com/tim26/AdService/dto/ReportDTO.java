package com.tim26.AdService.dto;

import com.tim26.AdService.model.Report;

public class ReportDTO {

    private Long id;
    private AdDTO adDTO;
    private String text;
    private double startKm;
    private double endKm;
    private String username;
    private double overLimit;
    private double price;
    private boolean paid;

    public ReportDTO(){

    }

    public ReportDTO(Long id, AdDTO adDTO, String text, double startKm, double endKm, String username, double overLimit, double price, boolean paid) {
        this.id = id;
        this.adDTO = adDTO;
        this.text = text;
        this.startKm = startKm;
        this.endKm = endKm;
        this.username = username;
        this.overLimit = overLimit;
        this.price = price;
        this.paid = paid;
    }

    public ReportDTO(Report report){
        AdDTO adDTO = new AdDTO(report.getAd());
        this.id = report.getId();
        this.adDTO = adDTO;
        this.text = report.getText();
        this.startKm = report.getStartKM();
        this.endKm = report.getEndKM();
        this.username = report.getUser().getUsername();
        this.paid = report.isPaid();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdDTO getAdDTO() {
        return adDTO;
    }

    public void setAdDTO(AdDTO adDTO) {
        this.adDTO = adDTO;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getStartKm() {
        return startKm;
    }

    public void setStartKm(double startKm) {
        this.startKm = startKm;
    }

    public double getEndKm() {
        return endKm;
    }

    public void setEndKm(double endKm) {
        this.endKm = endKm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getOverLimit() {
        return overLimit;
    }

    public void setOverLimit(double overLimit) {
        this.overLimit = overLimit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
