package com.tim26.demo.repository;

import com.tim26.demo.model.Ad;
import com.tim26.demo.model.Review;
import com.tim26.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByAd(Ad ad);
    List<Review> findAllByCreator(User user);

}
