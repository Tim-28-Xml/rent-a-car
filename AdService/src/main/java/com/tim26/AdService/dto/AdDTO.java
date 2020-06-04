package com.tim26.AdService.dto;

import com.tim26.AdService.model.Ad;

public class AdDTO {

    private CarDTO carDTO;
    private Long userId;
    private Long id;

    public AdDTO(){

    }

    public AdDTO(CarDTO carDTO,Long userId,Long id) {
        this.carDTO = carDTO;
        this.userId = userId;
        this.id = id;
    }

    public AdDTO(Ad ad){
        this(new CarDTO(ad.getCar()),ad.getUser().getId(),ad.getId());
    }

    public CarDTO getCarDTO() {
        return carDTO;
    }

    public void setCarDTO(CarDTO carDTO) {
        this.carDTO = carDTO;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
