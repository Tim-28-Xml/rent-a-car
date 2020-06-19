package com.tim26.Zuul.zuulserver.dto;

import java.time.LocalDate;

public class AdDateRange {

    private long id;

    private long ad_id;

    private LocalDate start;

    private LocalDate end;

    public AdDateRange() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getAd_id() {
        return ad_id;
    }

    public void setAd_id(long ad_id) {
        this.ad_id = ad_id;
    }
}
