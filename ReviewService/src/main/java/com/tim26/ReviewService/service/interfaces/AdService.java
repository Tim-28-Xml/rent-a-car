package com.tim26.ReviewService.service.interfaces;

import com.tim26.ReviewService.model.Ad;

import java.util.Optional;

public interface AdService {

    public Optional<Ad> findById(Long id);
}
