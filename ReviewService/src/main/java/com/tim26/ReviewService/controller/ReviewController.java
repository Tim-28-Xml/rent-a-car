package com.tim26.ReviewService.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.tim26.ReviewService.dto.ReviewDTO;
import com.tim26.ReviewService.service.interfaces.ReviewService;
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

    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = "/test")
    public String test() {
        System.out.println("hello secured");
        return "Hello svet from review service";
    }

    @GetMapping(value = "/by-ad-approved/{id}")
    public ResponseEntity<?> getAllByAdApproved(@PathVariable String id) {

        List<ReviewDTO> reviews = reviewService.getAllApprovedByAd(Long.parseLong(id));

        return  new ResponseEntity<>(reviews, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/by-ad/{id}")
    public ResponseEntity<?> getAllByAd(@PathVariable String id) {

        List<ReviewDTO> reviews = reviewService.getAllByAd(Long.parseLong(id));

        return  new ResponseEntity<>(reviews, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/all-unapproved")
    public ResponseEntity<?> getAllUnapproved() {

        List<ReviewDTO> reviews = reviewService.findAllUnapproved();

        return  new ResponseEntity<>(reviews, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping (value = "/approve")
    public ResponseEntity<?> approveReview(@RequestBody ReviewDTO reviewDTO) {

        boolean isdone = reviewService.approvetReview(reviewDTO);
        if(isdone){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasAuthority('CREATE_REVIEW')")
    @PostMapping(value= "/submit-response")
    public ResponseEntity<?> submitResponse(@RequestBody ReviewDTO dto, Principal p){

        reviewService.submitResponse(p,dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/decline/{id}")
    public ResponseEntity<?> declineReview(@PathVariable String id) {

        boolean isdone = reviewService.declineReview(Long.parseLong(id));
        if(isdone){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasAuthority('CREATE_REVIEW')")
    @PostMapping(value= "/submit-review")
    public ResponseEntity<?> submitReview(@RequestBody ReviewDTO dto, Principal p){

        reviewService.submitReview(p,dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value= "/my-reviews")
    public ResponseEntity<List<Long>> getUserReviews(Principal p){

          return new ResponseEntity<>(reviewService.getUserReviews(p),HttpStatus.OK);
    }

}
