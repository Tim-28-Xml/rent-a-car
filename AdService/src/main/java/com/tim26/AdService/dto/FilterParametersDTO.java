package com.tim26.AdService.dto;

import java.util.ArrayList;
import java.util.List;

public class FilterParametersDTO {

    private List<String> cities = new ArrayList<>();
    private List<String> brands = new ArrayList<>();
    private List<String> models = new ArrayList<>();
    private List<String> transmission = new ArrayList<>();
    private List<String> fuel = new ArrayList<>();
    private List<String> carClass = new ArrayList<>();
    private double minPrice = 0;
    private double maxPrice = 999999;
    private double minKm = 0;
    private double maxKm = 999999;
    private List<Integer> childSeats = new ArrayList<>();

    public FilterParametersDTO(){

    }

    public FilterParametersDTO(List<String> cities, List<String> brands, List<String> models, List<String> transmission, List<String> fuel, List<String> carClass) {
        this.cities = cities;
        this.brands = brands;
        this.models = models;
        this.transmission = transmission;
        this.fuel = fuel;
        this.carClass = carClass;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    public List<String> getModels() {
        return models;
    }

    public void setModels(List<String> models) {
        this.models = models;
    }

    public List<String> getTransmission() {
        return transmission;
    }

    public void setTransmission(List<String> transmission) {
        this.transmission = transmission;
    }

    public List<String> getFuel() {
        return fuel;
    }

    public void setFuel(List<String> fuel) {
        this.fuel = fuel;
    }

    public List<String> getCarClass() {
        return carClass;
    }

    public void setCarClass(List<String> carClass) {
        this.carClass = carClass;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinKm() {
        return minKm;
    }

    public void setMinKm(double minKm) {
        this.minKm = minKm;
    }

    public double getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(double maxKm) {
        this.maxKm = maxKm;
    }


    public List<Integer> getChildSeats() {
        return childSeats;
    }

    public void setChildSeats(List<Integer> childSeats) {
        this.childSeats = childSeats;
    }
}
