package com.tim26.demo.service.interfaces;

import com.tim26.demo.dto.ReviewDTO;

import java.security.Principal;
import java.util.List;

public interface ReviewService {

    public List<ReviewDTO> getAllApprovedByAd(Long id);
    public List<ReviewDTO> getAllByAd(Long id);
    public boolean approvetReview(ReviewDTO reviewDTO);
    public boolean declineReview(Long id);
    public List<ReviewDTO> findAllUnapproved();
    public boolean submitReview(Principal p, ReviewDTO  reviewDTO);
    public List<Long> getUserReviews(Principal p);


}
