package com.tim26.ReviewService.service.interfaces;

import com.tim26.ReviewService.dto.ReviewDTO;
import com.tim26.ReviewService.model.Ad;

import java.util.List;

public interface ReviewService {

    public List<ReviewDTO> getAllByAd(Long id);
}
