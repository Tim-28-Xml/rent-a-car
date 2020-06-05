package com.tim26.AdService.dto;

import com.tim26.AdService.model.Date;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateRangeDTO {

    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<DateDTO> dates = new ArrayList<>();

    public DateRangeDTO(Long id, LocalDate startDate, LocalDate endDate, List<DateDTO> dates) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dates = dates;
    }


    public DateRangeDTO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<DateDTO> getDates() {
        return dates;
    }

    public void setDates(List<DateDTO> dates) {
        this.dates = dates;
    }
}
