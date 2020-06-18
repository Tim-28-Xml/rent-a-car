package com.tim26.ReviewService.dto;

import com.tim26.ReviewService.model.Ad;
import com.tim26.ReviewService.model.Review;
import com.tim26.ReviewService.model.User;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class ReviewDTO {

    private long id;

    private String title;

    private String content;

    private double rating;

    private LocalDateTime time;


    //private Ad ad;

    private String creator;


    private boolean approved;


    public ReviewDTO(long id, String title, String content, double rating, LocalDateTime time, String creator, boolean approved) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.time = time;
        this.creator = creator;
        this.approved = approved;
    }

    public ReviewDTO(Review review){
        this.id = review.getId();
        this.title =  review.getTitle();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.time = review.getTime();
        this.creator = review.getCreator().getUsername();
        this.approved = review.isApproved();
    }


    public ReviewDTO(){}

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}