package com.tim26.demo.dto;

import com.tim26.demo.model.DateRange;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateAdDto {
    private String brand;
    private String model;
    private String fuel;
    private String transmission;
    private String carClass;
    private List<DateRange> dates;
    private double km;
    private double kmLimit;
    private boolean collision;
    private String childSeats;
    private List<String> files;
    private String city;
    private String pricelist;

    public CreateAdDto() {

    }

    public CreateAdDto(String brand, String model, String fuel, String transmission, String carClass, List<DateRange> dates, double km, double kmLimit, boolean collision, String childSeats, List<String> files, String city) {
        this.brand = brand;
        this.model = model;
        this.fuel = fuel;
        this.transmission = transmission;
        this.carClass = carClass;
        this.dates = dates;
        this.km = km;
        this.kmLimit = kmLimit;
        this.collision = collision;
        this.childSeats = childSeats;
        this.files = files;
        this.city = city;
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

    public String getChildSeats() {
        return childSeats;
    }

    public void setChildSeats(String childSeats) {
        this.childSeats = childSeats;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public List<DateRange> getDates() {
        return dates;
    }

    public void setDates(List<DateRange> dates) {
        this.dates = dates;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPricelist() {
        return pricelist;
    }

    public void setPricelist(String pricelist) {
        this.pricelist = pricelist;
    }
}
