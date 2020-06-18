package com.tim26.ReviewService.service;

import com.tim26.ReviewService.dto.ReviewDTO;
import com.tim26.ReviewService.model.Ad;
import com.tim26.ReviewService.model.Review;
import com.tim26.ReviewService.repository.AdRepository;
import com.tim26.ReviewService.repository.ReviewRepository;
import com.tim26.ReviewService.service.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AdRepository adRepository;


    @Override
    public List<ReviewDTO> getAllByAd(Long id) {

        List<ReviewDTO> reviews = new ArrayList<>();
        Ad ad = adRepository.findById(id).get();

       for(Review r: reviewRepository.findAllByAd(ad)){
           if(r.isApproved()) {
               ReviewDTO reviewDTO = new ReviewDTO(r);
               reviews.add(reviewDTO);
           }

       }

       return  reviews;

    }

    @Override
    public List<ReviewDTO> getAllByAd(Long id) {

        List<ReviewDTO> reviews = new ArrayList<>();
        Ad ad = adRepository.findById(id).get();

        for(Review r: reviewRepository.findAllByAd(ad)){

            ReviewDTO reviewDTO = new ReviewDTO(r);
            reviews.add(reviewDTO);


        }

        return  reviews;
    }

    @Override
    public boolean approvetReview(ReviewDTO dto) {

        Optional<Review> optional = reviewRepository.findById(dto.getId());
        Review review = optional.get();
        review.setApproved(true);
        reviewRepository.save(review);

        if(review.isApproved()){
            return  true;
        }else {
            return false;
        }
    }


    @Override
    public boolean declineReview(Long id) {

        Optional<Review> optional = reviewRepository.findById(id);
        Review review = optional.get();
       reviewRepository.delete(review);
       return true;
    }

    @Override
    public List<ReviewDTO> findAllUnapproved() {

        List<ReviewDTO> reviews = new ArrayList<>();

        for(Review r : reviewRepository.findAll()){

            if(r.isApproved() ==  false) {
                ReviewDTO reviewDTO = new ReviewDTO(r);
                reviews.add(reviewDTO);
            }
        }

        return reviews;
    }


}
