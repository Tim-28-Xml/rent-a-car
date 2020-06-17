package com.tim26.AdService.dto;

import javax.persistence.Column;
import java.time.LocalDate;

public class DateDTO {


    private long id;

    private LocalDate date;

    public DateDTO(long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    public DateDTO() {
    }

    public DateDTO(LocalDate date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
