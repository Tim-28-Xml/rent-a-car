package com.tim26.demo.dto;

import com.tim26.demo.model.Ad;

public class AdDTO {

    private CarDTO carDTO;
    private String username;

    public AdDTO(){

    }

    public AdDTO(CarDTO carDTO, String username) {
        this.carDTO = carDTO;
        this.username = username;
    }

    public AdDTO(Ad ad){
        this(new CarDTO(ad.getCar()),ad.getUser().getUsername());
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
}