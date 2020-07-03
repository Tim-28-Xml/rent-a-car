package com.tim26.Zuul.zuulserver.dto;

import java.time.LocalDate;

public class AdDTO {

    private CarDTO carDTO;
    private String username;
    private Long id;
    private String city;
    private LocalDate start;
    private LocalDate end;
    private CreatePricelistDto pricelistDto;

    public AdDTO(){

    }

    public AdDTO(AdDTO adDTO, LocalDate startDate, LocalDate endDate){
        this.carDTO = adDTO.carDTO;
        this.username = adDTO.username;
        this.id = adDTO.id;
        this.city = adDTO.city;
        this.start = startDate;
        this.end = endDate;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public CreatePricelistDto getPricelistDto() {
        return pricelistDto;
    }

    public void setPricelistDto(CreatePricelistDto pricelistDto) {
        this.pricelistDto = pricelistDto;
    }
}
