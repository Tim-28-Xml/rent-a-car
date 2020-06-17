package com.tim26.Zuul.zuulserver.dto;

import java.util.List;

public class CarDTO {

    private Long id;
    private String brand;
    private String model;
    private String fuel;
    private String transmission;
    private String carClass;
    private double km;
    private double kmLimit;
    private boolean cdw;
    private byte childSeats;
    private List<byte[]> files;
    private String city;

    public CarDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getKmLimit() {
        return kmLimit;
    }

    public void setKmLimit(double kmLimit) {
        this.kmLimit = kmLimit;
    }

    public boolean isCdw() {
        return cdw;
    }

    public void setCdw(boolean cdw) {
        this.cdw = cdw;
    }

    public byte getChildSeats() {
        return childSeats;
    }

    public void setChildSeats(byte childSeats) {
        this.childSeats = childSeats;
    }

    public List<byte[]> getFiles() {
        return files;
    }

    public void setFiles(List<byte[]> files) {
        this.files = files;
    }
}