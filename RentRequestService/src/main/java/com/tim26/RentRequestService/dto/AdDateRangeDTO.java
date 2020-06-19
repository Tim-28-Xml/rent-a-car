package com.tim26.RentRequestService.dto;

import com.tim26.RentRequestService.model.AdDateRange;

import javax.persistence.Column;
import java.time.LocalDate;

public class AdDateRangeDTO {

    private long id;


    private long ad_id;


    private LocalDate start;


    private LocalDate end;

    public AdDateRangeDTO() { }

    public AdDateRangeDTO(long id, long ad_id, LocalDate start, LocalDate end) {
        this.id = id;
        this.ad_id = ad_id;
        this.start = start;
        this.end = end;
    }

    public AdDateRangeDTO(AdDateRange adr) {
        this.id = adr.getId();
        this.ad_id = adr.getAd_id();
        this.start = adr.getStart();
        this.end = adr.getEnd();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAd_id() {
        return ad_id;
    }

    public void setAd_id(long ad_id) {
        this.ad_id = ad_id;
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
}
