package com.tim26.demo.dto;

import com.tim26.demo.model.Car;

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
    private int childSeats;
    private List<byte[]> files;

    public CarDTO(){

    }

    public CarDTO(Long id, String brand, String model, String fuel, String transmission, String carClass, double km, double kmLimit, boolean cdw, int childSeats, List<byte[]> files) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.fuel = fuel;
        this.transmission = transmission;
        this.carClass = carClass;
        this.km = km;
        this.kmLimit = kmLimit;
        this.cdw = cdw;
        this.childSeats = childSeats;
        this.files = files;
    }

    public CarDTO(Car car){
        this(car.getId(),car.getBrand(),car.getModel(),car.getFuel(),car.getTransmission(),car.getCarClass(),car.getKm(),car.getKmLimit(),car.isCdw(), (byte) car.getChildSeats(), car.getFiles());
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

    public int getChildSeats() {
        return childSeats;
    }

    public void setChildSeats(int childSeats) {
        this.childSeats = childSeats;
    }

    public List<byte[]> getFiles() {
        return files;
    }

    public void setFiles(List<byte[]> files) {
        this.files = files;
    }
}
