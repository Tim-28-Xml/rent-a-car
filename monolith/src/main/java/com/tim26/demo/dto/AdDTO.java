package com.tim26.demo.dto;

import com.tim26.demo.model.Ad;

public class AdDTO {

    private Long id;
    private CarDTO carDTO;
    private String username;
    private double rating;
    private int reviewNum;

    public AdDTO(){

    }

    public AdDTO(CarDTO carDTO, String username) {
        this.carDTO = carDTO;
        this.username = username;
    }

    public AdDTO(CarDTO carDTO, String username,double rating) {
        this.carDTO = carDTO;
        this.username = username;
        this.rating = rating;
    }

    public AdDTO(CarDTO carDTO, String username,int reviewNum) {
        this.carDTO = carDTO;
        this.username = username;
        this.rating = rating;
        this.reviewNum = reviewNum;
    }

    public AdDTO(CarDTO carDTO, String username, Long id) {
        this.carDTO = carDTO;
        this.username  = username;
        this.id = id;
    }

    public AdDTO(Ad ad){
        this(new CarDTO(ad.getCar()),ad.getUser().getUsername(), ad.getId());
    }

    public CarDTO getCarDTO() {
        return carDTO;
    }

    public void setCarDTO(CarDTO carDTO) {
        this.carDTO = carDTO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
