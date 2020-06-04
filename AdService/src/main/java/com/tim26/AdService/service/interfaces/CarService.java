package com.tim26.AdService.service.interfaces;

import com.tim26.AdService.dto.AdDTO;
import com.tim26.AdService.dto.CarDTO;
import org.springframework.stereotype.Service;


public interface CarService {

    CarDTO findById(Long id);
}
