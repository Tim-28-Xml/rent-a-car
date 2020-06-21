package com.tim26.ReviewService.service;

import com.sun.org.apache.regexp.internal.RE;
import com.tim26.ReviewService.dto.ReviewDTO;
import com.tim26.ReviewService.model.Ad;
import com.tim26.ReviewService.model.Review;
import com.tim26.ReviewService.model.User;
import com.tim26.ReviewService.repository.AdRepository;
import com.tim26.ReviewService.repository.ReviewRepository;
import com.tim26.ReviewService.service.interfaces.AdService;
import com.tim26.ReviewService.service.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AdService adService;


    @Override
    public List<ReviewDTO> getAllApprovedByAd(Long id) {

        List<ReviewDTO> reviews = new ArrayList<>();
        Ad ad = adService.findById(id).get();

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
        Ad ad = adService.findById(id).get();

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

    @Override
    public boolean submitReview(Principal p, ReviewDTO reviewDTO) {

        Ad ad = new Ad();
        ad.setId(reviewDTO.getAd_id());

        User u = new User();
        u.setUsername(p.getName());


        Review review = new Review(reviewDTO,ad,u);

        ad.getReviews().add(review);
        u.getReviews().add(review);

        reviewRepository.save(review);

        return true;
    }

    @Override
    public List<Long> getUserReviews(Principal p) {

        List<Review> reviews = reviewRepository.findAllByCreator(new User(p.getName()));
        List<Long> list_ids = new ArrayList<>();

        for(Review r :  reviews){

            list_ids.add(r.getAd().getId());

        }
        return list_ids;
    }

    @Override
    public boolean submitResponse(Principal p, ReviewDTO dto) {

        if(reviewRepository.findById(dto.getId()) ==  null){
            return false;
        }

        Optional<Review> review = reviewRepository.findById(dto.getId());

        Review review1 = review.get();
        review1.setResponse(dto.getResponse());

        return true;
    }



}
