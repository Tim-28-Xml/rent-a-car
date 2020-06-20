package com.tim26.demo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class AdDateRange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long ad_id;

    @Column
    private LocalDate start;

    @Column
    private LocalDate end_Date;

    public AdDateRange() {
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

    public LocalDate getEnd_Date() {
        return end_Date;
    }

    public void setEnd_Date(LocalDate end) {
        this.end_Date = end;
    }
}
