package com.tim26.ReviewService.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.tim26.ReviewService.dto.ReviewDTO;
import com.tim26.ReviewService.service.interfaces.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/review", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReviewController {

    private static final Logger LOGGER= LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = "/test")
    public String test() {
        System.out.println("hello secured");
        return "Hello svet from review service";
    }

    @GetMapping(value = "/by-ad-approved/{id}")
    public ResponseEntity<?> getAllByAdApproved(@PathVariable String id) {
        LOGGER.info("Getting approved reviews by Ad with id: {} : ", id);

        List<ReviewDTO> reviews = reviewService.getAllApprovedByAd(Long.parseLong(id));

        return  new ResponseEntity<>(reviews, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/by-ad/{id}")
    public ResponseEntity<?> getAllByAd(@PathVariable String id) {
        LOGGER.info("Getting all  reviews by Ad with id: {} : ", id);
        List<ReviewDTO> reviews = reviewService.getAllByAd(Long.parseLong(id));

        return  new ResponseEntity<>(reviews, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/all-unapproved")
    public ResponseEntity<?> getAllUnapproved() {
        LOGGER.info("Getting all unapproved reviews");
        List<ReviewDTO> reviews = reviewService.findAllUnapproved();

        return  new ResponseEntity<>(reviews, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping (value = "/approve")
    public ResponseEntity<?> approveReview(@RequestBody ReviewDTO reviewDTO) {
        LOGGER.info("Approving reviews with id: {} , creator: {}, written: {}, for ad with id : {} ", reviewDTO.getAd_id(),reviewDTO.getCreator(),reviewDTO.getTime(),reviewDTO.getAd_id());
        boolean isdone = reviewService.approvetReview(reviewDTO);
        if(isdone){
            LOGGER.info("Review with id : {} is approved", reviewDTO.getAd_id());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            LOGGER.info("Failed to approve review with id : {}", reviewDTO.getAd_id());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasAuthority('CREATE_REVIEW')")
    @PostMapping(value= "/submit-response")
    public ResponseEntity<?> submitResponse(@RequestBody ReviewDTO dto, Principal p){
        LOGGER.info("User with username: {} , submitting review written by user: {}, at time : {}, with content:{}, rating: {},for ad with id: {}",p.getName(), dto.getCreator(),dto.getTime(),dto.getContent(),
                dto.getRating(),dto.getAd_id());
        if(reviewService.submitResponse(p,dto)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            LOGGER.info("Falied to submit response by user: {} for review wwritten at time : {}, with content:{}, rating: {},for ad with id: {}",
                    p.getName(),dto.getTime(),dto.getContent(),dto.getRating(),dto.getAd_id());
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/decline/{id}")
    public ResponseEntity<?> declineReview(@PathVariable String id,Principal p) {
        LOGGER.info("Admin with username: {}, declining a review with id:{}", p.getName(),id);
        boolean isdone = reviewService.declineReview(Long.parseLong(id));
        if(isdone){
            LOGGER.info("Review with id : {} declined ",id );
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            LOGGER.info("Failed to decline review with id : {}",id );
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasAuthority('CREATE_REVIEW')")
    @PostMapping(value= "/submit-review")
    public ResponseEntity<?> submitReview(@RequestBody ReviewDTO dto, Principal p){
        LOGGER.info("User with username: {}, submitting review with content: {}, written: {}, with rating: {} for ad with id:{}",p.getName(),dto.getContent(),dto.getTime(),
                dto.getRating(),dto.getAd_id());
        reviewService.submitReview(p,dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value= "/my-reviews")
    public ResponseEntity<List<Long>> getUserReviews(Principal p){
            LOGGER.info("Getting all reviews from user with username: {}",p.getName() );
          return new ResponseEntity<>(reviewService.getUserReviews(p),HttpStatus.OK);
    }

}
