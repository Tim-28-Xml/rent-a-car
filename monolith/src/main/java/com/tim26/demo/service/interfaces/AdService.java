package com.tim26.demo.service.interfaces;

import com.tim26.demo.dto.AdDTO;
import com.tim26.demo.model.Ad;

import java.util.List;

public interface AdService {

    List<AdDTO> findAll();
    AdDTO findById(long id);
}
