package com.tim26.AdService.dto;

import com.tim26.AdService.model.Ad;
import com.tim26.AdService.model.Date;
import com.tim26.AdService.model.DateRange;

import java.util.ArrayList;
import java.util.List;

public class AdDTO {

    private CarDTO carDTO;
    private Long userId;
    private Long id;
    private ArrayList<DateRangeDTO>  rentDates = new ArrayList<>();
    private String city;

    public AdDTO(){

    }

    public AdDTO(CarDTO carDTO,Long userId,Long id,String city) {
        this.carDTO = carDTO;
        this.userId = userId;
        this.id = id;
        this.rentDates = rentDates;
        this.city = city;
    }

    public AdDTO(Ad ad){
        this(new CarDTO(ad.getCar()),ad.getUser().getId(),ad.getId(),ad.getCity());

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
}
