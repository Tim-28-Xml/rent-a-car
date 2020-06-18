package com.tim26.AdService.dto;

import com.tim26.AdService.model.Ad;
import com.tim26.AdService.model.Date;
import com.tim26.AdService.model.DateRange;

import java.util.ArrayList;
import java.util.List;

public class AdDTO {

    private CarDTO carDTO;
    private String username;
    private Long id;
    private ArrayList<DateRangeDTO>  rentDates = new ArrayList<>();
    private String city;
    private String pricelist;

    public AdDTO(){

    }

    public AdDTO(CarDTO carDTO,String username,Long id,String city) {
        this.carDTO = carDTO;
        this.username = username;
        this.id = id;
        this.rentDates = rentDates;
        this.city = city;
    }

    public AdDTO(Ad ad){
        this(new CarDTO(ad.getCar()),ad.getUser().getUsername(),ad.getId(),ad.getCity());

        for(DateRange dt : ad.getRentDates()){

            List<DateDTO> datesdto = new ArrayList<>();

            for(Date d : dt.getDates()){
                datesdto.add(new DateDTO(d.getId(),d.getDate()));
            }


            DateRangeDTO dto = new DateRangeDTO(dt.getId(),dt.getStartDate(),dt.getEndDate(),datesdto);
            this.getRentDates().add(dto);
        }
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

    public ArrayList<DateRangeDTO> getRentDates() {
        return rentDates;
    }

    public void setRentDates(ArrayList<DateRangeDTO> rentDates) {
        this.rentDates = rentDates;
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
