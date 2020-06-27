package com.tim26.ReviewService.service;

import com.tim26.ReviewService.model.Ad;
import com.tim26.ReviewService.repository.AdRepository;
import com.tim26.ReviewService.service.interfaces.AdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdServiceImpl.class);

    @Autowired
    private AdRepository adRepository;

    @Override
    public Optional<Ad> findById(Long id) {
        LOGGER.info("Getting ad with id: {}", id);
        return adRepository.findById(id);
    }
}
