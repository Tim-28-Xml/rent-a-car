package com.tim26.ReviewService.model;

import com.tim26.ReviewService.dto.ReviewDTO;
import com.tim26.ReviewService.model.Ad;
import com.tim26.ReviewService.model.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private double rating;

    @Column
    private LocalDateTime time;

    @ManyToOne
    private Ad ad;

    @ManyToOne
    private User creator;

    @Column
    private boolean approved = false;

    @Column
    private String response;

    public Review() {
    }

    public Review(ReviewDTO dto,Ad ad,User u){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.rating = dto.getRating();
        this.time = LocalDateTime.now();
        this.ad = ad;
        this.creator = u;
        this.approved = false;
        this.response =  dto.getResponse();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
