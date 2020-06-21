package com.tim26.demo.dto;

import com.tim26.demo.model.Review;

public class ReviewDTO {

    private long id;

    private String title;

    private String content;

    private double rating;

    private String time;

    private Long ad_id;

    private String creator;

    private String response;


    private boolean approved;


    public ReviewDTO(String   r,long id, String title, String content, double rating, String time, String creator, boolean approved) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.time = time;
        this.creator = creator;
        this.approved = approved;
        this.response=r;
    }

    public ReviewDTO(Review review){
        this.id = review.getId();
        this.title =  review.getTitle();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.time = review.getTime().toString();
        this.creator = review.getCreator().getUsername();
        this.approved = review.isApproved();
        this.ad_id = review.getAd().getId();
        this.response = review.getResponse();
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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

    public Long getAd_id() {
        return ad_id;
    }

    public void setAd_id(Long ad_id) {
        this.ad_id = ad_id;
    }


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
