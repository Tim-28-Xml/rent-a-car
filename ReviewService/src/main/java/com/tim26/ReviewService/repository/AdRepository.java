package com.tim26.ReviewService.repository;

import com.tim26.ReviewService.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Long> {

    Optional<Ad> findById(Long id);
}
