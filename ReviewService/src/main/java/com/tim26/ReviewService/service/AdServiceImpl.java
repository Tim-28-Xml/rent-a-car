package com.tim26.ReviewService.service;

import com.tim26.ReviewService.model.Ad;
import com.tim26.ReviewService.repository.AdRepository;
import com.tim26.ReviewService.service.interfaces.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository adRepository;

    @Override
    public Optional<Ad> findById(Long id) {
        return adRepository.findById(id);
    }
}
